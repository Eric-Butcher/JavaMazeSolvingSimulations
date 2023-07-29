package model.generators;

import utilities.Constants;

import java.util.ArrayList;

public class PrimGenerator extends Generator{

    private ArrayList<Cell> frontier = new ArrayList<>(Constants.mazeLength * Constants.mazeLength);

    private boolean startStepDone = false;

    public PrimGenerator(){
        super();
    };

    private void startStep(){
        Cell startCell = this.getRandomGridCell();
        startCell.initializeCell();
        ArrayList<Cell> adjacentCells = this.getAdjacentCells(startCell);
        frontier.addAll(adjacentCells);
    }
    public void iterate(){
        if (!startStepDone){
            startStep();
            return;
        } else if (frontier.size() == 0){
            return;
        } else {
            Cell chosen = this.getRandomCellFromList(frontier);
            ArrayList<Cell> adjacentCells = this.getAdjacentCells(chosen);
            // make function to get list of cells that are/arenot initiailzed
            ArrayList<Cell> initializedNeighbors = this.getInitializedCells(adjacentCells);
            Cell initializedNeighobor = this.getRandomCellFromList(initializedNeighbors);
            this.clearPathBetweenCells(chosen, initializedNeighobor);
            chosen.initializeCell();
            return;
        }
    }

    public void finish(){
        while ((frontier.size() == 0) || (!startStepDone)){
            this.iterate();
        }
    }

    /*
     * Prims Algorithm Explained Step-by-Step for Maze Generation
     *
     *  Step 1.
     *  Start with a grid full of walled-off cells.
     *
     *  Step 2. (our startStep)
     *  Pick any cell on the maze at random and mark it as initialized.
     *  Add the 4 cells it is adjacent to, to the frontier list.
     *
     *  Step 3.
     *  While there are cells in the frontier list:
     *      1. Pop a cell from the frontier list randomly.
     *      2. Generate a list of all adjacent cells that are initialized.
     *      3. Pick one of these initialized cells at random.
     *      4. Form a path (delete the wall/s ) between the frontier cell and the initialized cell.
     *      5. Set the frontier cell as initialized.
     *
     *
     *  When there are no more frontier cells the maze is complete.
     *
     */
}
