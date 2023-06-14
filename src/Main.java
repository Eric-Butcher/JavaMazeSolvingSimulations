import javax.swing.*;
import java.awt.*;

public class Main {

    final static int MAZE_WINDOW_SIZE = 480; // Maze GUI will be 480x480 pixels
    final static int MAZE_LENGTH = 16; // Number of cells in a row in the square maze

    final static int CELL_LENGTH = MAZE_WINDOW_SIZE / MAZE_LENGTH;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Maze-Solving Simulations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(MAZE_WINDOW_SIZE, MAZE_WINDOW_SIZE + 8);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(16, 16));
//        frame.setLayout(null);


        Cell[][] cells = new Cell[16][16];

        for (int i = 0; i < MAZE_LENGTH; i++)
        {
            for (int j = 0; j < MAZE_LENGTH; j++)
            {
                Cell cell = new Cell(CELL_LENGTH, i, j);
                cells[i][j] = cell;
                frame.add(cells[i][j]);
            }
        }



    }
}
