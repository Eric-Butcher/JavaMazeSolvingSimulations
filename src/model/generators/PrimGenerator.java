package model.generators;

import utilities.Constants;

import java.util.ArrayList;

public class PrimGenerator extends Generator{

    private final ArrayList<Cell> frontier = new ArrayList<>(Constants.mazeLength * Constants.mazeLength);

    private boolean startStepDone = false;

    public PrimGenerator(){
        super();
    };

    private void startStep(){
        Cell startCell = this.getRandomGridCell();
        startCell.initializeCell();
        ArrayList<Cell> adjacentCells = this.getAdjacentCells(startCell);
        frontier.addAll(adjacentCells);
        this.startStepDone = true;
    }
    public void iterate(){
        if (!startStepDone){
            startStep();
            return;
        } else if (frontier.isEmpty()){
            return;
        } else {
            Cell chosen = Generator.popRandomCellFromList(frontier);

            ArrayList<Cell> adjacentCells = this.getAdjacentCells(chosen);
            ArrayList<Cell> initializedNeighbors = Generator.getInitializedCells(adjacentCells);

            Cell initializedNeighbor = Generator.popRandomCellFromList(initializedNeighbors);
            this.clearPathBetweenCells(chosen, initializedNeighbor);

            chosen.initializeCell();

            ArrayList<Cell> uninitializedNeighbors = Generator.getUnInitializedCells(adjacentCells);
            frontier.addAll(uninitializedNeighbors);

            return;
        }
    }

    public void finish(){
        while ((!frontier.isEmpty()) || (!startStepDone)){
            this.iterate();
        }
        return;
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
     *      6. Add the uninitialized adjacent cells to the frontier cell to the frontier list.
     *
     *
     *  When there are no more frontier cells the maze is complete.
     *
     */
}
