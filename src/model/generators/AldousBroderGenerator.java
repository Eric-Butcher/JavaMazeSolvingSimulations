package model.generators;

import controller.TileUpdate;
import controller.ViewUpdatePacket;
import model.Cell;
import utilities.Constants;

import java.util.ArrayList;
import java.util.LinkedList;

public class AldousBroderGenerator extends Generator {

    private final int maxInitialized = Constants.mazeLength * Constants.mazeLength;
    private int cellsInitialized = 0;
    private Cell currentCell;

    public AldousBroderGenerator() {
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

        return updatePacket;
    }

    private void startStep() {
        currentCell = this.getGrid().getRandomGridCell();
        currentCell.initializeCell();
        cellsInitialized++;
    }

    @Override
    public void iterate() {
        if (this.cellsInitialized >= (maxInitialized)) {
            this.currentCell = null;
            this.setDone();
        } else if (cellsInitialized == 0) {
            this.startStep();
        } else {
            ArrayList<Cell> neighbors = this.getGrid().getAdjacentCells(currentCell);
            Cell chosen = popRandomCellFromList(neighbors);
            if (!chosen.isInitialized()) {
                this.getGrid().createPathBetweenCells(currentCell, chosen);
                chosen.initializeCell();

                cellsInitialized++;
            }
            currentCell = chosen;

        }
    }

    @Override
    public void finish() {
//        while (this.cellsInitialized < this.maxInitialized){
//            this.iterate();
//        }
//        this.iterate();
        while (!this.getDoneGenerating()) {
            this.iterate();
        }
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
