package view;

import javax.swing.*;

public class FinishButton extends JButton {

    public FinishButton() {
        this.setText("Finish 🏁");
//        this.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
