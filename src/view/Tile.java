package view;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    private int borderThickness = 2;
    private boolean hasTopBorder = true;
    private boolean hasBottomBorder = true;
    private boolean hasRightBorder = true;
    private boolean hasLeftBorder = true;
    private boolean hasMouse = false;
    private boolean isInitialized = false;
    private boolean isTraversed = false;
    private boolean isHighlighted = false;
    private boolean isCurrent = false;
    private int tileLength = 30;
    private int xPos;
    private int yPos;

    public Tile(int tileLength, int xPos, int yPos) {
        this.setVisible(true);
        this.tileLength = tileLength;
        this.xPos = xPos;
        this.yPos = yPos;
        String text = "" + xPos + yPos;
        this.add(new JTextField((text)));
        this.setSize(this.tileLength, this.tileLength);
        this.setBounds(this.xPos * this.tileLength, this.yPos * this.tileLength, this.tileLength, this.tileLength);
//        this.setBackground(Color.BLACK);
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }

    public void setTileBorders(boolean hasTopBorder, boolean hasRightBorder, boolean hasBottomBorder, boolean hasLeftBorder) {
        this.hasTopBorder = hasTopBorder;
        this.hasRightBorder = hasRightBorder;
        this.hasBottomBorder = hasBottomBorder;
        this.hasLeftBorder = hasLeftBorder;
    }

    public boolean isHasTopBorder() {
        return hasTopBorder;
    }

    public boolean isHasBottomBorder() {
        return hasBottomBorder;
    }

    public boolean isHasRightBorder() {
        return hasRightBorder;
    }

    public boolean isHasLeftBorder() {
        return hasLeftBorder;
    }

    public boolean isHasMouse() {
        return hasMouse;
    }

    public void setHasMouse(boolean hasMouse) {
        this.hasMouse = hasMouse;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public boolean isTraversed() {
        return isTraversed;
    }

    public void setTraversed(boolean traversed) {
        isTraversed = traversed;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    @Override
    public void paint(Graphics graphics) {

        // Determining what color to paint the cell
        // Is current - blue
        // Is highlighted - yellow
        // Is traversed - something
        // Is initialized - white
        // Is none of these things

        // We will reset highlighting and isCurrent after each iteration
        // in case tile gets bumped out of those states
        // This way we don't have to return an entire maze worth of info each time
        if (this.isCurrent()){
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, this.tileLength, this.tileLength);
            this.setCurrent(false);
        } else if (this.isHighlighted()){
            graphics.setColor(Color.RED);
            graphics.fillRect(0, 0, this.tileLength, this.tileLength);
            this.setHighlighted(false);
        } else if (this.isTraversed()){
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(0, 0, this.tileLength, this.tileLength);
        } else if (this.isInitialized()){
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, this.tileLength, this.tileLength);
        } else {
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(0, 0, this.tileLength, this.tileLength);
        }

        graphics.setColor(Color.CYAN);
        if (this.hasTopBorder) {
            graphics.fillRect(0, 0, this.tileLength, this.borderThickness);
        }
        if (this.hasBottomBorder) {
            graphics.fillRect(0, this.tileLength - this.borderThickness, this.tileLength, this.borderThickness);
        }
        if (this.hasLeftBorder) {
            graphics.fillRect(0, 0, this.borderThickness, this.tileLength);
        }
        if (this.hasRightBorder) {
            graphics.fillRect(this.tileLength - this.borderThickness, 0, this.borderThickness, this.tileLength);
        }

    }
}