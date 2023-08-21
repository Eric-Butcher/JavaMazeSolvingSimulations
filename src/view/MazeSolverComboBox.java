package view;

import model.solvers.SolverAlgorithms;

import javax.swing.*;

public class MazeSolverComboBox extends JComboBox {

    final static String[] mazeSolverAlgorithmChoices = {"Prim", "Aldous-Broder", "Random DFS", "Wilson"};

    public MazeSolverComboBox() {
        super(SolverAlgorithms.values());
        this.setVisible(true);
        this.setEnabled(true);
    }
}
