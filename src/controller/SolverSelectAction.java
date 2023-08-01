package controller;

import model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolverSelectAction implements ActionListener {

    private final Model model;

    public SolverSelectAction(Model model){
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String algo = ((JComboBox)e.getSource()).getSelectedItem().toString();
//        this.model.changeSelectedSolvingAlgo(algo);
    }
}
