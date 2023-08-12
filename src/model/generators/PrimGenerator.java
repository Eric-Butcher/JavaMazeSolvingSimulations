package model.generators;

import controller.TileUpdate;
import controller.ViewUpdatePacket;
import model.Cell;
import utilities.Constants;

import java.util.ArrayList;
import java.util.LinkedList;

public class PrimGenerator extends Generator{

    private final ArrayList<Cell> frontier = new ArrayList<>(Constants.mazeLength * Constants.mazeLength);

    private boolean startStepDone = false;

    public PrimGenerator(){
        super();
    };

    public ArrayList<Cell> getFrontier() {
        return frontier;
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
        boolean inFrontier;

        for (int x = Constants.minCellIndex; x <= Constants.maxCellIndex; x++){
            for (int y = Constants.minCellIndex; y <= Constants.maxCellIndex; y++){

                Cell cell = this.getCell(x, y);
                inFrontier = frontier.contains(cell);

                TileUpdate tileUpdate = makeTileUpdateFromCell(cell, false, inFrontier);
                updatePacket.addTileUpdate(tileUpdate);
            }
        }
        return updatePacket;
    }

    private void startStep(){
        Cell startCell = this.getRandomGridCell();
        startCell.initializeCell();
        ArrayList<Cell> adjacentCells = this.getAdjacentCells(startCell);
        this.getFrontier().addAll(adjacentCells);
        this.setStartStepDone(true);
    }
    public void iterate(){
        if (!this.isStartStepDone()){
            startStep();
            return;
        } else if (this.getFrontier().isEmpty()){
            this.setDone();
            return;
        } else {
//            1. Pop a cell from the frontier list randomly.
            Cell chosen = Generator.popRandomCellFromList(this.getFrontier());

//            2. Generate a list of all adjacent cells that are initialized.
            ArrayList<Cell> adjacentCells = this.getAdjacentCells(chosen);
            ArrayList<Cell> initializedNeighbors = Generator.getInitializedCells(adjacentCells);

//          3. Pick one of these initialized cells at random.
            Cell initializedNeighbor = Generator.popRandomCellFromList(initializedNeighbors);

//          4. Form a path (delete the wall/s ) between the frontier cell and the initialized cell.
            this.createPathBetweenCells(chosen, initializedNeighbor);

//          5. Set the frontier cell as initialized.
            chosen.initializeCell();

            ArrayList<Cell> uninitializedNeighbors = Generator.getUnInitializedCells(adjacentCells);
            for (Cell uninitialized : uninitializedNeighbors){
                if (!this.getFrontier().contains(uninitialized)){
                    this.getFrontier().add(uninitialized);
                }
            }
            return;
        }
    }

    public void finish(){
//        while ((!this.getFrontier().isEmpty()) || (!this.isStartStepDone())){
//            this.iterate();
//        }
        while(!this.getDoneGenerating()){
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
     *      6. Add cells to the frontier list that are adjacent to the chosen frontier cell and not yet in frontier.
     *
     *
     *  When there are no more frontier cells the maze is complete.
     *
     */
}
