package controller;

import model.Model;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolveAction implements ActionListener {

    private final Model model;
    private final View view;

    public SolveAction(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.solve();
        this.view.updateView(this.model.updateView());
    }
}
