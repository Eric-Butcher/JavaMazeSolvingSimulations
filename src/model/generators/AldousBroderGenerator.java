package model.generators;

import controller.TileUpdate;
import controller.ViewUpdatePacket;
import utilities.Constants;

import java.util.ArrayList;
import java.util.LinkedList;

public class AldousBroderGenerator extends Generator{

    private int cellsInitialized = 0;
    private final int maxInitialized = Constants.mazeLength * Constants.mazeLength;

    private Cell currentCell;

    public AldousBroderGenerator(){
        super();
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    @Override
    public ViewUpdatePacket makeViewUpdatePacket() {
        ViewUpdatePacket updatePacket = new ViewUpdatePacket(new LinkedList<>());

        for (int x = Constants.minCellIndex; x <= Constants.maxCellIndex; x++){
            for (int y = Constants.minCellIndex; y <= Constants.maxCellIndex; y++){

                Cell cell = this.getCell(x, y);
                TileUpdate tileUpdate = makeTileUpdateFromCell(cell, false, false);
                updatePacket.addTileUpdate(tileUpdate);
            }
        }

        // Add the current cell at the end, will override its earlier addition
        TileUpdate tileUpdate = makeTileUpdateFromCell(this.getCurrentCell(), true, false);
        updatePacket.addTileUpdate(tileUpdate);
        return updatePacket;
    }

    private void startStep(){
        currentCell = this.getRandomGridCell();
        currentCell.initializeCell();
        cellsInitialized++;
    }

    @Override
    public void iterate(){
        if (this.cellsInitialized >= (maxInitialized)){
            return;
        } else if (cellsInitialized == 0){
            this.startStep();
        }
        else {
            ArrayList<Cell> neighbors = this.getAdjacentCells(currentCell);
            Cell chosen = popRandomCellFromList(neighbors);
            if (!chosen.isInitialized()){
                this.clearPathBetweenCells(currentCell, chosen);
                chosen.initializeCell();

                cellsInitialized++;
            }
            currentCell = chosen;
            return;

        }
    }

    @Override
    public void finish(){
        while (this.cellsInitialized < this.maxInitialized){
            this.iterate();
        }
        return;
    }

    /*
    * The Aldous Broder Maze Generating Algorithm:
    *
    *   Step 1.
    *   Chose cell at random in the maze. Mark this cell as initialized and make it your current cell.
    *
    *   Step 2.
    *   While all cells have not been initialized:
    *       Pick an adjacent cell to your current cell.
    *       If this chosen cell is uninitialized, form a path between your current cell and the picked cell.
    *       Make the chosen cell you current cell.
    *
    *
    *   That's it! Remarkably simple, but very slow!
    * */
}
