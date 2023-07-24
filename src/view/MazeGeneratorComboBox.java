package view;

import javax.swing.*;
import java.awt.*;

public class MazeGeneratorComboBox extends JComboBox {

    final static String[] mazeGeneratorAlgorithmChoices = {"A", "B", "C", "D"};
    public MazeGeneratorComboBox(DisplayFrame parent)
    {
        super(mazeGeneratorAlgorithmChoices);
        this.addActionListener(parent);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
