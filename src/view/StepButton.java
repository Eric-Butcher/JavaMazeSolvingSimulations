package view;

import javax.swing.*;

public class StepButton extends JButton {

    public StepButton() {
        this.setText("Step 🐾");
//        this.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
