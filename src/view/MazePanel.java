package view;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private final Tile[][] tiles = new Tile[16][16];

    public MazePanel(int mazeLength, int cellLength, int menuLength, int mazePanelSize) {
        this.setBackground(Color.CYAN);
        this.setVisible(true);
        this.setLayout(new GridLayout(16, 16));
//        this.setBounds(menuLength, 0, mazePanelSize, mazePanelSize);
        this.setPreferredSize(new Dimension(mazePanelSize, mazePanelSize));


        for (int y = 0; y < mazeLength; y++) {
            for (int x = 0; x < mazeLength; x++) {
                Tile tile = new Tile(cellLength, x, y);
                this.tiles[y][x] = tile;
                this.add(tiles[y][x]);
            }
        }
    }

    public Tile getTile(int xPos, int yPos) {
        return this.tiles[yPos][xPos];
    }
}
