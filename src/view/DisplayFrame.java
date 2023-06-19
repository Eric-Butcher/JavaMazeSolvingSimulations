package view;

import javax.swing.*;
import java.awt.*;

public class DisplayFrame extends JFrame {
    final static int MAZE_PANEL_SIZE = 480; // Maze GUI will be 480x480 pixels

    final static int MENU_PANEL_SIZE = 120;
    final static int MAZE_LENGTH = 16; // Number of cells in a row in the square maze
    final static int CELL_LENGTH = MAZE_PANEL_SIZE / MAZE_LENGTH;

    private MenuPanel menuPanel;
    private MazePanel mazeFrame;

    public DisplayFrame()
    {
        this.setResizable(false);
        this.setTitle("Maze-Solving Simulations");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(MENU_PANEL_SIZE + MAZE_PANEL_SIZE, MAZE_PANEL_SIZE + 8);
        this.setLayout(new BorderLayout());


        this.menuPanel = new MenuPanel(MENU_PANEL_SIZE, MAZE_PANEL_SIZE);
        this.add(this.menuPanel, BorderLayout.WEST);
        this.mazeFrame = new MazePanel(MAZE_LENGTH, CELL_LENGTH, MENU_PANEL_SIZE, MAZE_PANEL_SIZE);
        this.add(this.mazeFrame);

        this.setVisible(true);


    }
}
