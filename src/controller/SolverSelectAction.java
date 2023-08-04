package controller;

import model.Model;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolverSelectAction implements ActionListener {

    private final Model model;
    private final View view;

    public SolverSelectAction(Model model, View view){
        this.model = model;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String algo = ((JComboBox)e.getSource()).getSelectedItem().toString();
//        this.model.changeSelectedSolvingAlgo(algo);
    }
}
