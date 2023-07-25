package view;

import javax.swing.*;

public class ClearButton extends JButton {

    public ClearButton()
    {
//        this.addActionListener(parent);
        this.setText("Clear Maze");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
