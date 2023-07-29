package model.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import utilities.Constants;

public abstract class Generator {

    private Cell[][] grid = new Cell[16][16];

    public Generator(){

        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 16; j++){
                Cell cell = new Cell(i, j);
                this.grid[i][j] = cell;
            }
        }
    }

    protected Cell getCell(int xLoc, int yLoc){
        if (((xLoc < 0) || (xLoc > 16)) || ((yLoc < 0) || (yLoc > 16))){
            throw new IllegalArgumentException("Gave location(s) outside of maze bounds. ");
        };
        return this.grid[xLoc][yLoc];
    }

    protected Cell getRandomGridCell() {

        int from = 0;
        int to = 16;

        int x = from - new Random().nextInt(Math.abs(to - from));
        int y = from - new Random().nextInt(Math.abs(to - from));

        return this.getCell(x, y);
    }

    public void clearPathBetweenCells(Cell from, Cell to){

        int fromX = from.getxPos();
        int fromY = from.getyPos();
        int toX = to.getxPos();
        int toY = to.getyPos();

        // Probably need to check how JavaSwing is rendering the cells
        if ((fromY == toY) && (fromX > toY)){
            from.removeLeftBorder();
            to.removeRightBorder();
        } else if ((fromY == toY) && (fromX < toX)){
            from.removeRightBorder();
            to.removeLeftBorder();
        } else if ((fromX == toX) && (fromY > toY)){
            from.removeTopBorder();
            to.removeTopBorder();
        } else if ((fromX == toX) && (fromY < toY)){
            from.removeTopBorder();
            to.removeBottomBorder();
        } else {
            throw new IllegalStateException("Cells provided have malformed coordinates.");
        }

    }


    protected Cell getRandomCellFromList(List<Cell> list) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(list.size());
        return list.remove(randomIndex);
    }

    protected ArrayList<Cell> getInitializedCells(List<Cell> list){
        ArrayList<Cell> retVal = new ArrayList<>(4);
        for (Cell cell : list){
            if (cell.isInitialized()){
                retVal.add(cell);
            }
        }
        return retVal;
    }

    protected ArrayList<Cell> getUnInitializedCells(List<Cell> list){
        ArrayList<Cell> retVal = new ArrayList<>(4);
        for (Cell cell : list){
            if (!cell.isInitialized()){
                retVal.add(cell);
            }
        }
        return retVal;
    }

    protected ArrayList<Cell> getAdjacentCells(Cell centerCell){
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


    public abstract void iterate();

    public abstract void finish();


}
