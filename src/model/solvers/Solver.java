package model.solvers;

import model.Cell;
import utilities.Constants;

import java.util.ArrayList;
import java.util.List;

public abstract class Solver {

    private Cell[][] grid = new Cell[Constants.mazeLength][Constants.mazeLength];

    private boolean done = false;
    protected Cell startPoint;
    protected ArrayList<Cell> endPoints;

    public Solver(Cell[][] grid){
        this.grid = grid;
        this.startPoint = this.grid[0][0];

        ArrayList<Cell> ends = new ArrayList<>();
        if ((Constants.mazeLength % 2) == 0){
            ends.add(this.grid[Constants.maxCellIndex/2][Constants.maxCellIndex/2]);
            ends.add(this.grid[Constants.maxCellIndex/2 + 1][Constants.maxCellIndex/2]);
            ends.add(this.grid[Constants.maxCellIndex/2][Constants.maxCellIndex/2 + 1]);
            ends.add(this.grid[Constants.maxCellIndex/2 + 1][Constants.maxCellIndex/2 + 1]);
        } else {
            ends.add(this.grid[Constants.maxCellIndex/2][Constants.maxCellIndex/2]);
        }


        this.endPoints = ends;
    }

    public Solver(Cell[][] grid, Cell startPoint, ArrayList<Cell> endPoints){
        this.grid = grid;
        this.startPoint = startPoint;
        this.endPoints = endPoints;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Cell getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Cell startPoint) {
        this.startPoint = startPoint;
    }

    public ArrayList<Cell> getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(ArrayList<Cell> endPoints) {
        this.endPoints = endPoints;
    }

    public Cell getCell(int xLoc, int yLoc){
        if (((xLoc < 0) || (xLoc > 16)) || ((yLoc < 0) || (yLoc > 16))){
            throw new IllegalArgumentException("Gave location(s) outside of maze bounds. ");
        };
        return this.grid[xLoc][yLoc];
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

    public static ArrayList<Cell> getUnTraversedCells(List<Cell> list){
        ArrayList<Cell> retVal = new ArrayList<>(4);
        for (Cell cell : list){
            if (!cell.isTraversed()){
                retVal.add(cell);
            }
        }
        return retVal;
    }

    public static boolean isTherePathBetweenCells(Cell from, Cell to){

        int fromX = from.getxPos();
        int fromY = from.getyPos();
        int toX = to.getxPos();
        int toY = to.getyPos();

        if (((fromY == toY) && (fromX > toX)) && ((!from.isLeftBorder()) && (!to.isRightBorder()))){
            return true;
        } else if (((fromY == toY) && (fromX < toX)) && ((!from.isRightBorder()) && (!to.isLeftBorder()))){
            return true;
        } else if (((fromX == toX) && (fromY > toY)) && ((!from.isTopBorder()) && (!to.isBottomBorder()))){
            return true;
        } else if (((fromX == toX) && (fromY < toY)) && ((!from.isBottomBorder()) && (!to.isTopBorder()))){
            return true;
        }
        return false;

    }

    public ArrayList<Cell> getUntraversedReachableNeighbors(Cell center){
        ArrayList<Cell> adjacents = getAdjacentCells(center);
        ArrayList<Cell> untraversed = getUnTraversedCells(adjacents);
        ArrayList<Cell> retVal = new ArrayList<>();
        for (Cell cell : untraversed){
            if (isTherePathBetweenCells(center, cell)){
                retVal.add(cell);
            }
        }

        return untraversed;
    }

    public boolean atDestination(Cell current){
        for (Cell destination : endPoints){
            if (current.equals(destination)){
                return true;
            }
        }
        return false;
    }




    public abstract void iterate();

    public abstract void finish();
}
