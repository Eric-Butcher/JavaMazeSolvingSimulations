package model;

import controller.TileUpdate;

import java.util.Objects;

public class Cell {

    private final int xPos;
    private final int yPos;
    private boolean isInitialized = false;
    private boolean topBorder = true;
    private boolean rightBorder = true;
    private boolean bottomBorder = true;
    private boolean leftBorder = true;
    private boolean isTraversed = false;

    private boolean isGoal = false;

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public boolean isTopBorder() {
        return topBorder;
    }

    public boolean isRightBorder() {
        return rightBorder;
    }

    public boolean isBottomBorder() {
        return bottomBorder;
    }

    public boolean isLeftBorder() {
        return leftBorder;
    }

    public boolean isTraversed() {
        return isTraversed;
    }

    public boolean isGoal() {
        return isGoal;
    }

    public void setGoal(boolean goal) {
        isGoal = goal;
    }

    public Cell(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void initializeCell() {
        this.isInitialized = true;
    }

    public void removeTopBorder() {
        this.topBorder = false;
    }

    public void removeRightBorder() {
        this.rightBorder = false;
    }

    public void removeBottomBorder() {
        this.bottomBorder = false;
    }

    public void removeLeftBorder() {
        this.leftBorder = false;
    }

    public void setTraversed(boolean traversed) {
        this.isTraversed = traversed;
    }

    public static TileUpdate makeTileUpdateFromCell(Cell cell, boolean isCurrent, boolean toHighlight) {
        TileUpdate retVal = new TileUpdate(cell.getxPos(), cell.getyPos(), cell.isTopBorder(), cell.isRightBorder(), cell.isBottomBorder(), cell.isLeftBorder(), false, cell.isInitialized(), cell.isTraversed(), toHighlight, isCurrent, cell.isGoal());
        return retVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;
        return getxPos() == cell.getxPos() && getyPos() == cell.getyPos() && isInitialized() == cell.isInitialized() && isTopBorder() == cell.isTopBorder() && isRightBorder() == cell.isRightBorder() && isBottomBorder() == cell.isBottomBorder() && isLeftBorder() == cell.isLeftBorder() && isTraversed() == cell.isTraversed();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getxPos(), getyPos());
    }
}
