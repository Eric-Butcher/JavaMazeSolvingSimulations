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

    public boolean isHighlighted() {
        return isHighlighted;
    }

    private int tileLength = 30;

    private int xPos;
    private int yPos;

    public Tile(int tileLength, int xPos, int yPos)
    {
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

    public void setTileBorders(boolean hasTopBorder, boolean hasRightBorder, boolean hasBottomBorder, boolean hasLeftBorder){
        this.hasTopBorder = hasTopBorder;
        this.hasRightBorder = hasRightBorder;
        this.hasBottomBorder = hasBottomBorder;
        this.hasLeftBorder = hasLeftBorder;
    }

    public void setHasMouse(boolean hasMouse) {
        this.hasMouse = hasMouse;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public void setTraversed(boolean traversed) {
        isTraversed = traversed;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
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

    public boolean isInitialized() {
        return isInitialized;
    }

    public boolean isTraversed() {
        return isTraversed;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    @Override
    public void paint(Graphics g)
    {
        if (this.isInitialized){
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.tileLength, this.tileLength);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, this.tileLength, this.tileLength);
        }

        g.setColor(Color.CYAN);
        if (this.hasTopBorder)
        {
            g.fillRect(0, 0, this.tileLength, this.borderThickness);
        }
        if (this.hasBottomBorder)
        {
            g.fillRect(0, this.tileLength - this.borderThickness, this.tileLength, this.borderThickness);
        }
        if (this.hasLeftBorder)
        {
            g.fillRect(0, 0, this.borderThickness, this.tileLength);
        }
        if (this.hasRightBorder)
        {
            g.fillRect(this.tileLength - this.borderThickness, 0, this.borderThickness, this.tileLength);
        }

    }
}