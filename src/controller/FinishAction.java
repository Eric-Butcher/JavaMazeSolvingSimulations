package controller;

import model.Model;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishAction implements ActionListener {

    private final Model model;
    private final View view;

    public FinishAction(Model model, View view){
        this.model = model;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Finish pressed.");
        this.model.finish();
        // TODO
        this.view.updateView(this.model.updateView());
    }
}
