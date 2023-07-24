package view;

import javax.swing.JPanel;
import java.awt.*;

public class Tile extends JPanel {

    private int borderThickness = 2;

    private boolean hasTopBorder = true;
    private boolean hasBottomBorder = true;
    private boolean hasRightBorder = true;
    private boolean hasLeftBorder = true;

    private int tileLength = 30;

    private int xPos;
    private int yPos;

    public Tile(int tileLength, int xPos, int yPos)
    {
        this.setVisible(true);
        this.tileLength = tileLength;
        this.xPos = xPos;
        this.yPos = yPos;
        this.setSize(this.tileLength, this.tileLength);
        this.setBounds(this.xPos * this.tileLength, this.yPos * this.tileLength, this.tileLength, this.tileLength);
//        this.setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.tileLength, this.tileLength);


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