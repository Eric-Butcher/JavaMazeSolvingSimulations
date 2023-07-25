package controller;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetAction implements ActionListener {

    private final Model model;

    public ResetAction(Model model){
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Testing Resetting Button!");
//        this.model.resetMaze();
    }
}
