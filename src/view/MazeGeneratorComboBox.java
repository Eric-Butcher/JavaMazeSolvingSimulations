package view;

import model.generators.GeneratorAlgorithms;

import javax.swing.*;

public class MazeGeneratorComboBox extends JComboBox {

//    final static String[] mazeGeneratorAlgorithmChoices = {"A", "B", "C", "D"};

    public MazeGeneratorComboBox()
    {
        super(GeneratorAlgorithms.values());
        this.setVisible(true);
        this.setEnabled(true);
    }
}
