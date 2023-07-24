package view;

import javax.swing.*;

public class MazeGeneratorComboBox extends JComboBox {

    final static String[] mazeGeneratorAlgorithmChoices = {"A", "B", "C", "D"};
    public MazeGeneratorComboBox(View parent)
    {
        super(mazeGeneratorAlgorithmChoices);
        this.addActionListener(parent);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
