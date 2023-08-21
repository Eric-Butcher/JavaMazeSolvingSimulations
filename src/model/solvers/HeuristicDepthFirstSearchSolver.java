package model.solvers;

import controller.TileUpdate;
import controller.ViewUpdatePacket;
import model.Cell;
import model.Grid;
import utilities.Constants;

import java.util.*;

public class HeuristicDepthFirstSearchSolver extends Solver {

    // The child is the key and the parent is the value
    private HashMap<Cell, Cell> parentCells = new HashMap<>(Constants.mazeLength * Constants.mazeLength);
    private Stack<Cell> stack = new Stack<>();
    private boolean startStepDone = false;
    private Cell currentCell;
    private Cell targetCell;

    public HeuristicDepthFirstSearchSolver(Grid grid) {
        super(grid);
        this.currentCell = startPoint;
    }

    public HeuristicDepthFirstSearchSolver(Grid grid, Cell startPoint, ArrayList<Cell> endPoints) {
        super(grid, startPoint, endPoints);
        this.currentCell = startPoint;
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
        ViewUpdatePacket updatePacket = new ViewUpdatePacket(new LinkedList<>());


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

    private final Comparator<Cell> hueristicComparator = new Comparator<Cell>() {
        @Override
        public int compare(Cell o1, Cell o2) {
            return Integer.compare(heuristic(o1), heuristic(o2));
        }
    };

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

    public ArrayList<Cell> generateOrderedStackAppendList(Cell currentCell) {
        ArrayList<Cell> neighbors = getUntraversedReachableNeighbors(currentCell);
        neighbors.sort(Collections.reverseOrder(hueristicComparator)); // We want the closest to be ontop of the stack
        return neighbors;
    }

    public void iterate() {
        if (this.isDone()) {
            return;
        } else if (!startStepDone) {
            this.currentCell.setTraversed(true);
            List<Cell> neighbors = generateOrderedStackAppendList(currentCell);
            this.stack.addAll(neighbors);
            targetCell = stack.pop();
            this.setStartStepDone(true);
            return;
        } else if (atDestination(currentCell)) {
            this.setDone(true);
            return;
        } else if (Grid.isTherePathBetweenCells(currentCell, targetCell)) {
            parentCells.put(targetCell, currentCell);
            currentCell = targetCell;
            currentCell.setTraversed(true);
            ArrayList<Cell> neighbors = generateOrderedStackAppendList(currentCell);
            stack.addAll(neighbors);
            targetCell = stack.pop();
        } else {
            currentCell = parentCells.get(currentCell);
        }
    }

    public void finish() {
        while (!this.isDone()) {
            this.iterate();
        }
        return;
    }

    /* Heuristic Depth-First Search Explained for Maze Solving
     * Step 1.
     * Create an empty stack. Mark starting cell as visited.
     * Push the reachable neighbors of the starting cell to the stack ordered by the heuristic. (Lowest cost on top)
     *
     * Step 2.
     * While current_cell is not one of our destination cells:
     *   1. Pop a cell from the stack. This is our target cell.
     *   2. If we can move to the target cell from the current cell:
     *       a. Assign the parentage of the target cell as current cell
     *       b. Make current cell the target cell
     *       c. Mark (now) current cell as traversed
     *       d. Push all un-traversed reachable neighbors of current cell to the stack according to the heuristic. (Lowest cost on top)
     *   3. Else (we cannot move to target cell from current cell):
     *       a. Make current cell equal to the parent of current cell
     *
     */
}
