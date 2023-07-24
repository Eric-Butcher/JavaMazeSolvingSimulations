package view;

import javax.swing.*;
import java.awt.*;

public class StepButton extends JButton {

    public StepButton(DisplayFrame parent)
    {
        this.addActionListener(parent);
        this.setText("🐾");
        this.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
