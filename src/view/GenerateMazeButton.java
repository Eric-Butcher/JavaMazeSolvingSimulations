package view;

import javax.swing.*;
import java.awt.*;

public class GenerateMazeButton extends JButton {

    public GenerateMazeButton(DisplayFrame parent)
    {

        this.addActionListener(parent);
        this.setText("Generate Maze");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }

}
