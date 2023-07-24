package view;

import javax.swing.*;
import java.awt.*;

public class ClearButton extends JButton {

    public ClearButton(DisplayFrame parent)
    {
        this.addActionListener(parent);
        this.setText("Clear Maze");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
