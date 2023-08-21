package controller;

import model.Model;
import model.solvers.Solver;
import model.solvers.SolverAlgorithms;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolverSelectAction implements ActionListener {

    private final Model model;
    private final View view;

    public SolverSelectAction(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String algoString = ((JComboBox) e.getSource()).getSelectedItem().toString();
        SolverAlgorithms algoEnum = SolverAlgorithms.valueOf(algoString);
        Class<Solver> algoClass = (Class<Solver>) algoEnum.getClazz();
        this.model.changeSelectedSolvingAlgo(algoClass);
    }
}
