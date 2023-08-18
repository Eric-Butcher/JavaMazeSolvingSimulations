package model.solvers;

import controller.ViewUpdatePacket;
import model.Cell;
import model.Grid;
import utilities.Constants;

import java.util.ArrayList;
import java.util.List;

public abstract class Solver {

//    private Cell[][] grid = new Cell[Constants.mazeLength][Constants.mazeLength];

    private Grid grid;
    private boolean done = false;
    protected Cell startPoint;
    protected ArrayList<Cell> endPoints;

    public Solver(Grid grid){
        this.grid = grid;
        this.startPoint = this.grid.getCell(0, 0);

        ArrayList<Cell> ends = new ArrayList<>();
        if ((Constants.mazeLength % 2) == 0){
            ends.add(this.grid.getCell(Constants.maxCellIndex/2, Constants.maxCellIndex/2));
            ends.add(this.grid.getCell(Constants.maxCellIndex/2 + 1,Constants.maxCellIndex/2));
            ends.add(this.grid.getCell(Constants.maxCellIndex/2, Constants.maxCellIndex/2 + 1));
            ends.add(this.grid.getCell(Constants.maxCellIndex/2 + 1, Constants.maxCellIndex/2 + 1));
        } else {
            ends.add(this.grid.getCell(Constants.maxCellIndex/2, Constants.maxCellIndex/2));
        }


        this.endPoints = ends;
    }

    public Solver(Grid grid, Cell startPoint, ArrayList<Cell> endPoints){
        this.grid = grid;
        this.startPoint = startPoint;
        this.endPoints = endPoints;
    }

    public Grid getGrid() {
        return grid;
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
    public static ArrayList<Cell> getUnTraversedCells(List<Cell> list){
        ArrayList<Cell> retVal = new ArrayList<>(4);
        for (Cell cell : list){
            if (!cell.isTraversed()){
                retVal.add(cell);
            }
        }
        return retVal;
    }

    public ArrayList<Cell> getUntraversedReachableNeighbors(Cell center){
        ArrayList<Cell> adjacents = this.grid.getAdjacentCells(center);
        ArrayList<Cell> untraversed = getUnTraversedCells(adjacents);
        ArrayList<Cell> retVal = new ArrayList<>();
        for (Cell cell : untraversed){
            if (Grid.isTherePathBetweenCells(center, cell)){
                retVal.add(cell);
            }
        }

        return retVal;
    }

    public boolean atDestination(Cell current){
        for (Cell destination : endPoints){
            if (current.equals(destination)){
                return true;
            }
        }
        return false;
    }

    public abstract ViewUpdatePacket makeViewUpdatePacket();
    public abstract void iterate();

    public abstract void finish();
}
