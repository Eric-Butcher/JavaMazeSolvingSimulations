package view;

import javax.swing.*;
import java.awt.*;

public class SolveMazeButton extends JButton {

    public SolveMazeButton(DisplayFrame parent)
    {
        this.addActionListener(parent);
        this.setText("Solve Maze");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
