package view;

import javax.swing.*;

public class MazeSolverComboBox extends JComboBox {

    final static String[] mazeSolverAlgorithmChoices = {"Prim", "Aldous-Broder", "Random DFS", "Wilson"};
    public MazeSolverComboBox()
    {
        super(mazeSolverAlgorithmChoices);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
