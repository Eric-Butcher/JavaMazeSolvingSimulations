package controller;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StepAction implements ActionListener {

    private final Model model;

    public StepAction(Model model){
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.step();
    }
}
