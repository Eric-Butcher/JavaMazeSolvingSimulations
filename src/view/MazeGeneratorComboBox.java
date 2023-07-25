package view;

import javax.swing.*;

public class MazeGeneratorComboBox extends JComboBox {

    final static String[] mazeGeneratorAlgorithmChoices = {"A", "B", "C", "D"};
    public MazeGeneratorComboBox()
    {
        super(mazeGeneratorAlgorithmChoices);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
