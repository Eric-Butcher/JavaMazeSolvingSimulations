package view;

import javax.swing.*;

public class ClearButton extends JButton {

    public ClearButton()
    {
//        this.addActionListener(parent);
        this.setText("Clear");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
