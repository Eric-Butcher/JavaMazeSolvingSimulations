package model.solvers;

import controller.TileUpdate;
import controller.ViewUpdatePacket;
import model.Cell;
import model.Grid;
import utilities.Constants;

import java.util.*;

public class GreedyBestFirstSearchSolver extends Solver {


    private final Comparator<Cell> hueristicComparator = new Comparator<Cell>() {
        @Override
        public int compare(Cell o1, Cell o2) {
            return Integer.compare(heuristic(o1), heuristic(o2));
        }
    };

    private final Queue<Cell> queue = new PriorityQueue<>(hueristicComparator);
    private boolean startStepDone = false;
    private Cell currentCell;
    private Cell targetCell;

    private Stack<Cell> backtrace = new Stack<>();

    public GreedyBestFirstSearchSolver(Grid grid) {
        super(grid);
    }

    public GreedyBestFirstSearchSolver(Grid grid, Cell startPoint, ArrayList<Cell> endPoints) {
        super(grid, startPoint, endPoints);
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public boolean isStartStepDone() {
        return startStepDone;
    }

    public void setStartStepDone(boolean startStepDone) {
        this.startStepDone = startStepDone;
    }

    @Override
    public ViewUpdatePacket makeViewUpdatePacket() {
        ViewUpdatePacket updatePacket = new ViewUpdatePacket(new ArrayList<>(300));


        for (int x = Constants.minCellIndex; x <= Constants.maxCellIndex; x++) {
            for (int y = Constants.minCellIndex; y <= Constants.maxCellIndex; y++) {

                Cell cell = this.getGrid().getCell(x, y);


                TileUpdate tileUpdate = Cell.makeTileUpdateFromCell(cell, false, false);
                updatePacket.addTileUpdate(tileUpdate);
            }
        }

        // Add the current cell at the end, will override its earlier addition
        if (currentCell != null) {
            TileUpdate tileUpdate = Cell.makeTileUpdateFromCell(this.getCurrentCell(), true, false);
            updatePacket.addTileUpdate(tileUpdate);
        }

        if (targetCell != null) {
            TileUpdate tileUpdate = Cell.makeTileUpdateFromCell(this.targetCell, false, true);
            updatePacket.addTileUpdate(tileUpdate);
        }


        return updatePacket;
    }


    //Used for testing
    public Comparator<Cell> getHueristicComparator() {
        return hueristicComparator;
    }

    public int manhattanDistance(Cell current, Cell target) {
        int dx = Math.abs(current.getxPos() - target.getxPos());
        int dy = Math.abs(current.getyPos() - target.getyPos());
        int retVal = dx + dy;
        return retVal;
    }

    public int heuristic(Cell cell) {
        int minDistance = Integer.MAX_VALUE;
        int calculatedDistance = 0;
        for (Cell target : this.endPoints) {
            calculatedDistance = manhattanDistance(cell, target);
            minDistance = Math.min(minDistance, calculatedDistance);
        }
        return minDistance;
    }

    public Stack<Cell> generateBacktrace(Cell startingCell, Cell endingCell) {
        // Bread-First Search for Backtracking
        // Add starting cell to queue and to visited list
        // While queue is not empty:
        // Find all reachable traversed neighbors that are not visited
        // Mark their source as the current cell
        // Add them to the queue
        // If we have found the endingCell break
        // Use a while loop to continually append to a list by getting parents until you get to startingCell
        // Return the list

        Queue<Cell> queue = new LinkedList<>();
        HashSet<Cell> visited = new HashSet<>();
        queue.add(startingCell);
        visited.add(startingCell);

        HashMap<Cell, Cell> sources = new HashMap<>(Constants.mazeLength * Constants.mazeLength);

        boolean searchComplete = false;
        while (!queue.isEmpty() && !searchComplete) {
            Cell current = queue.poll();
            for (Cell neighbor : getTraversedReachableNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    sources.putIfAbsent(neighbor, current);
                    queue.add(neighbor);

                    if (Grid.isTherePathBetweenCells(neighbor, endingCell)) {
                        sources.putIfAbsent(endingCell, neighbor);
                        searchComplete = true;
                        break;
                    }
                }
            }
        }

        Stack<Cell> retVal = new Stack<>();
        Cell appendee = sources.get(endingCell);
        while (!appendee.equals(startingCell)) {
            retVal.add(appendee);
            appendee = sources.get(appendee);
        }
        return retVal;
    }


    public void iterate() {
        if (this.isDone()) {
        } else if (!startStepDone) {
            this.currentCell = startPoint;
            this.currentCell.setTraversed(true);
            List<Cell> neighbors = getUntraversedReachableNeighbors(currentCell);
            this.queue.addAll(neighbors);
            targetCell = queue.poll();
            this.setStartStepDone(true);
        } else if (atDestination(currentCell)) {
            this.targetCell = null;
            this.setDone(true);
        } else if (Grid.isTherePathBetweenCells(currentCell, targetCell)) {
//            parentCells.put(targetCell, currentCell);
            currentCell = targetCell;
            currentCell.setTraversed(true);
            ArrayList<Cell> neighbors = getUntraversedReachableNeighbors(currentCell);
            queue.addAll(neighbors);
            targetCell = queue.poll();
        } else if (!backtrace.isEmpty()) {
            // Pop the next move off of backtrace and move there
            currentCell = backtrace.pop();
        } else {
            // Generate the backtrace for the currentCell and targetCell
            backtrace = generateBacktrace(currentCell, targetCell);

            // Pop the next move off of backtrace and move there
            currentCell = backtrace.pop();
        }
    }


    public void finish() {
        while (!this.isDone()) {
            this.iterate();
        }
    }

    /* Greedy Best-First-Search Explained for Maze Solving
     * Step 1.
     * Create an empty priority queue. Mark starting cell as visited.
     * Push the reachable neighbors of the starting cell to the queue.
     *
     * Step 2.
     * While current_cell is not one of our destination cells:
     *   1. Poll a cell from the stack. This is our target cell.
     *   2. If we can move to the target cell from the current cell:
     *       a. Assign the parentage of the target cell as current cell
     *       b. Make current cell the target cell
     *       c. Mark (now) current cell as traversed
     *       d. Push all un-traversed reachable neighbors of current cell to the queue.
     *   3. Else (we cannot move to target cell from current cell):
     *       a. Make current cell equal to the parent of current cell
     *
     */


}
