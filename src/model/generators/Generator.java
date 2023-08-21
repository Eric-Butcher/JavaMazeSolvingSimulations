package model.generators;

import controller.ViewUpdatePacket;
import model.Cell;
import model.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Generator {

//    private Cell[][] grid = new Cell[Constants.mazeLength][Constants.mazeLength];

    private Grid grid;

    private boolean done = false;

    protected void doneGenerating() {
        this.done = true;
    }

    public Generator() {

//        for (int i = 0; i < 16; i++){
//            for (int j = 0; j < 16; j++){
//                Cell cell = new Cell(i, j);
//                this.grid[i][j] = cell;
//            }
//        }

        this.grid = new Grid();
    }

    public Grid getGrid() {
        return grid;
    }

    public boolean getDoneGenerating() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public static Cell popRandomCellFromList(List<Cell> list) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(list.size());
        return list.remove(randomIndex);
    }

    public static ArrayList<Cell> getInitializedCells(List<Cell> list) {
        ArrayList<Cell> retVal = new ArrayList<>(4);
        for (Cell cell : list) {
            if (cell.isInitialized()) {
                retVal.add(cell);
            }
        }
        return retVal;
    }

    public static ArrayList<Cell> getUnInitializedCells(List<Cell> list) {
        ArrayList<Cell> retVal = new ArrayList<>(4);
        for (Cell cell : list) {
            if (!cell.isInitialized()) {
                retVal.add(cell);
            }
        }
        return retVal;
    }

    public abstract ViewUpdatePacket makeViewUpdatePacket();

    public abstract void iterate();

    public abstract void finish();


}
