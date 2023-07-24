package view;

import javax.swing.*;

public class MazeSolverComboBox extends JComboBox {

    final static String[] mazeSolverAlgorithmChoices = {"Prim", "Aldous-Broder", "Random DFS", "Wilson"};
    public MazeSolverComboBox(View parent)
    {
        super(mazeSolverAlgorithmChoices);
        this.addActionListener(parent);
        this.setVisible(true);
        this.setEnabled(true);
    }
}
