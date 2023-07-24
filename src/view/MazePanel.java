package view;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel{



    public MazePanel(int mazeLength, int cellLength, int menuLength, int mazePanelSize)
    {
        this.setBackground(Color.CYAN);
        this.setVisible(true);
        this.setLayout(new GridLayout(16, 16));
//        this.setBounds(menuLength, 0, mazePanelSize, mazePanelSize);
        this.setPreferredSize(new Dimension(mazePanelSize, mazePanelSize));

        Cell[][] cells = new Cell[16][16];

        for (int i = 0; i < mazeLength; i++)
        {
            for (int j = 0; j < mazeLength; j++)
            {
                Cell cell = new Cell(cellLength, i, j);
                cells[i][j] = cell;
                this.add(cells[i][j]);
            }
        }
    }
}
