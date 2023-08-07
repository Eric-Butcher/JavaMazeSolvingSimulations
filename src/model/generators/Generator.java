package model.generators;

import controller.TileUpdate;
import controller.ViewUpdatePacket;
import utilities.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Generator {

    private Cell[][] grid = new Cell[16][16];

    private boolean done = false;

    protected void doneGenerating(){
        this.done = true;
    }

    public Generator(){

        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 16; j++){
                Cell cell = new Cell(i, j);
                this.grid[i][j] = cell;
            }
        }
    }

    public boolean getDoneGenerating(){
        return this.done;
    }

    public void setDone(){
        this.done = true;
    }

    public Cell getCell(int xLoc, int yLoc){
        if (((xLoc < 0) || (xLoc > 16)) || ((yLoc < 0) || (yLoc > 16))){
            throw new IllegalArgumentException("Gave location(s) outside of maze bounds. ");
        };
        return this.grid[xLoc][yLoc];
    }

    public Cell getRandomGridCell() {

        int from = 0;
        int to = 16;

        int x = ThreadLocalRandom.current().nextInt(from, to);
        int y = ThreadLocalRandom.current().nextInt(from, to);

        return this.getCell(x, y);
    }

    public void clearPathBetweenCells(Cell from, Cell to){

        int fromX = from.getxPos();
        int fromY = from.getyPos();
        int toX = to.getxPos();
        int toY = to.getyPos();

        if ((fromY == toY) && (fromX > toX)){
            from.removeLeftBorder();
            to.removeRightBorder();
        } else if ((fromY == toY) && (fromX < toX)){
            from.removeRightBorder();
            to.removeLeftBorder();
        } else if ((fromX == toX) && (fromY > toY)){
            from.removeTopBorder();
            to.removeBottomBorder();
        } else if ((fromX == toX) && (fromY < toY)){
            from.removeBottomBorder();
            to.removeTopBorder();
        } else {
            throw new IllegalStateException("Cells provided have malformed coordinates.");
        }

    }


    public static Cell popRandomCellFromList(List<Cell> list) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(list.size());
        return list.remove(randomIndex);
    }

    public static ArrayList<Cell> getInitializedCells(List<Cell> list){
        ArrayList<Cell> retVal = new ArrayList<>(4);
        for (Cell cell : list){
            if (cell.isInitialized()){
                retVal.add(cell);
            }
        }
        return retVal;
    }

    public static ArrayList<Cell> getUnInitializedCells(List<Cell> list){
        ArrayList<Cell> retVal = new ArrayList<>(4);
        for (Cell cell : list){
            if (!cell.isInitialized()){
                retVal.add(cell);
            }
        }
        return retVal;
    }

    public ArrayList<Cell> getAdjacentCells(Cell centerCell){
        int centerX = centerCell.getxPos();
        int centerY = centerCell.getyPos();

        int newXIndex;
        int newYIndex;

        ArrayList<Cell> adjacentCells = new ArrayList<>(4);

        for (int xBump = -1; xBump < 2; xBump++){
            for (int yBump = -1; yBump < 2; yBump++){

                if (Math.abs(xBump) != Math.abs(yBump)){
                    newXIndex = centerX + xBump;
                    newYIndex = centerY + yBump;
                    if ((newXIndex >= Constants.minCellIndex) &&
                            (newXIndex <= Constants.maxCellIndex) &&
                            (newYIndex >= Constants.minCellIndex) &&
                            (newYIndex <= Constants.maxCellIndex)
                    ){
                        Cell adjacent = this.getCell(newXIndex, newYIndex);
                        adjacentCells.add(adjacent);
                    }

                }
            }
        }

        return adjacentCells;
    }

    public static TileUpdate makeTileUpdateFromCell(Cell cell, boolean isCurrent, boolean toHighlight){
        TileUpdate retVal = new TileUpdate(cell.getxPos(), cell.getyPos(), cell.isTopBorder(), cell.isRightBorder(), cell.isBottomBorder(), cell.isLeftBorder(), false, cell.isInitialized(), false, toHighlight, isCurrent);
        return retVal;
    }

    public abstract ViewUpdatePacket makeViewUpdatePacket();
    public abstract void iterate();

    public abstract void finish();


}
