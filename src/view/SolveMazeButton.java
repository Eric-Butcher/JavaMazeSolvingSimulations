package view;

import javax.swing.*;

public class SolveMazeButton extends JButton {

    public SolveMazeButton(View parent)
    {
        this.addActionListener(parent);
        this.setText("Solve Maze");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
