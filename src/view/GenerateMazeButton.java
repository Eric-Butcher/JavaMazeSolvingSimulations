package view;

import javax.swing.*;

public class GenerateMazeButton extends JButton {

    public GenerateMazeButton(View parent)
    {

        this.addActionListener(parent);
        this.setText("Generate Maze");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }

}
