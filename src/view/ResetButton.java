package view;

import javax.swing.*;

public class ResetButton extends JButton {

    public ResetButton(View parent)
    {
        this.addActionListener(parent);
        this.setText("Reset All");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
