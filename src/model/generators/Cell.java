package model.generators;

public class Cell {

    private final int xPos;
    private final int yPos;
    private boolean isInitialized = false;
    private boolean topBorder = true;
    private boolean rightBorder = true;
    private boolean bottomBorder = true;
    private boolean leftBorder = true;
    private boolean traversed = false;

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
        return traversed;
    }

    public Cell(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void initializeCell(){
        this.isInitialized = true;
    }

    public void removeTopBorder(){
        this.topBorder = false;
    }

    public void removeRightBorder(){
        this.rightBorder = false;
    }

    public void removeBottomBorder(){
        this.bottomBorder = false;
    }

    public void removeLeftBorder(){
        this.leftBorder = false;
    }


}
