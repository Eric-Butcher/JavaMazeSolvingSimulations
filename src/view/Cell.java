package view;

import javax.swing.JPanel;
import java.awt.*;

public class Cell extends JPanel {

    private int borderThickness = 2;

    private boolean hasTopBorder = true;
    private boolean hasBottomBorder = true;
    private boolean hasRightBorder = true;
    private boolean hasLeftBorder = true;

    private int cellLength = 30;

    private int xPos;
    private int yPos;

    public Cell(int cellLength, int xPos, int yPos)
    {
        this.setVisible(true);
        this.cellLength = cellLength;
        this.xPos = xPos;
        this.yPos = yPos;
        this.setSize(this.cellLength, this.cellLength);
        this.setBounds(this.xPos * this.cellLength, this.yPos * this.cellLength, this.cellLength, this.cellLength);
//        this.setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.cellLength, this.cellLength);


        g.setColor(Color.CYAN);
        if (this.hasTopBorder)
        {
            g.fillRect(0, 0, this.cellLength, this.borderThickness);
        }
        if (this.hasBottomBorder)
        {
            g.fillRect(0, this.cellLength - this.borderThickness, this.cellLength, this.borderThickness);
        }
        if (this.hasLeftBorder)
        {
            g.fillRect(0, 0, this.borderThickness, this.cellLength);
        }
        if (this.hasRightBorder)
        {
            g.fillRect(this.cellLength - this.borderThickness, 0, this.borderThickness, this.cellLength);
        }

    }
}