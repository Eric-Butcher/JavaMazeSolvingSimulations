package view;

import javax.swing.*;
import java.awt.*;

public class ResetButton extends JButton {

    public ResetButton(DisplayFrame parent)
    {
        this.addActionListener(parent);
        this.setText("Reset All");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
