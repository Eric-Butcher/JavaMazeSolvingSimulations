package view;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel{

    private Tile[][] tiles = new Tile[16][16];

    public MazePanel(int mazeLength, int cellLength, int menuLength, int mazePanelSize)
    {
        this.setBackground(Color.CYAN);
        this.setVisible(true);
        this.setLayout(new GridLayout(16, 16));
//        this.setBounds(menuLength, 0, mazePanelSize, mazePanelSize);
        this.setPreferredSize(new Dimension(mazePanelSize, mazePanelSize));


        for (int i = 0; i < mazeLength; i++)
        {
            for (int j = 0; j < mazeLength; j++)
            {
                Tile tile = new Tile(cellLength, i, j);
                this.tiles[i][j] = tile;
                this.add(tiles[i][j]);
            }
        }
    }

    public Tile getTile(int xPos, int yPos){
        return this.tiles[xPos][yPos];
    }
}
