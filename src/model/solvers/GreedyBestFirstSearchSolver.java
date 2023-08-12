package model.solvers;

import model.Cell;
import utilities.Constants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Stack;

public class GreedyBestFirstSearchSolver extends Solver{

    // The child is the key and the parent is the value
    private HashMap<Cell, Cell> parentCells = new HashMap<>(Constants.mazeLength * Constants.mazeLength);
    private Stack<Cell> stack = new Stack<>();
    private boolean startStepDone = false;

    private Cell currentCell;
    public GreedyBestFirstSearchSolver(Cell[][] grid){
        super(grid);
    }

    public GreedyBestFirstSearchSolver(Cell[][] grid, Cell startPoint, ArrayList<Cell> endPoints){
        super(grid, startPoint, endPoints);
    }

    public int manhattenDistance(Cell current, Cell target){
        int dx = Math.abs(current.getxPos() - target.getxPos());
        int dy = Math.abs(current.getyPos()) - target.getyPos();
        int retVal = dx + dy;
        return retVal;
    }

    public int heuristic(Cell cell){
        int minDistance = 0;
        int calculatedDistance = 0;
        for (Cell target : this.endPoints){
            calculatedDistance = manhattenDistance(cell, target);
            minDistance = Math.min(minDistance, calculatedDistance);
        }
        return minDistance;
    }

    private final Comparator<Cell> hueristicComparator = new Comparator<Cell>() {
        @Override
        public int compare(Cell o1, Cell o2) {
            return heuristic(o2) - heuristic(o1);
        }
    };



    public void iterate(){
        if (!startStepDone){
            currentCell = startPoint;
            this.currentCell.setTraversed(true);
            ArrayList<Cell> neighbors = getUntraversedReachableNeighbors(currentCell);
            neighbors.sort(hueristicComparator);
            stack.addAll(neighbors);
            return;
        }
        else if (atDestination(currentCell)){
            this.setDone(true);
            return;
        }

        Cell targetCell = stack.pop();
        if (isTherePathBetweenCells(currentCell, targetCell)){
//            a. Assign the parentage of the target cell as current cell
            parentCells.put(targetCell, currentCell);
//            b. Make current cell the target cell
            currentCell = targetCell;
//            c. Mark (now) current cell as traversed
            currentCell.setTraversed(true);

            //d. Push all un-traversed reachable neighbors of current cell to the stack according to the heuristic. (Lowest cost on top)
            ArrayList<Cell> neighbors = getUntraversedReachableNeighbors(currentCell);
            neighbors.sort(hueristicComparator);
            stack.addAll(neighbors);
        } else {
            currentCell = parentCells.get(currentCell);
        }
    }

    public void finish(){
        while (!this.isDone()){
            this.iterate();
        }
        return;
    }

    /* Greedy Best-First-Search Explained for Maze Solving
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

    /*
    * # Create the maze
# Create the mouse
# Create an empty stack that will be used to store the possible progressions in the search
# Push the first node onto the stack
# Repeat the following steps until the stack is empty (every visitable cell has been visited) or destination found
# Pop a node from the stack and mark it as visited, set it as our target_cell
# If we are currently on this cell:
# do nothing, this is our first move

# If we are not neighboring the cell on a face
# While current_cell is not neighboring target_cell:
# Move the mouse to current_cell.parent, re-render
# current_cell = current_cell.parent
# (Else)If we are neighboring the target_cell on a face
# Move the mouse to the target_cell
# current_cell = target_cell
# If current_cell is our destination:
# QUIT
# ELSE
# For each unvisited neighbor of the current_cell, push that neighbor to the stack
    * */

}
