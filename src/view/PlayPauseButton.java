package view;

import javax.swing.*;
import java.awt.*;

public class PlayPauseButton extends JButton {

    public PlayPauseButton()
    {
//        this.setText(" ⏯ ");
        this.setText(" 🏁"); // For now playPause will act as a finish button until I get to the multithreading stuff
        this.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
