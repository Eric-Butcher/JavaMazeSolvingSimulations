package view;

import javax.swing.*;

public class PlayPauseButton extends JButton {

    public PlayPauseButton() {
        this.setText("Play/Pause ⏯");
        this.setFocusable(false);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
