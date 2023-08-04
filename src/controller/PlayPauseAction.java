package controller;

import model.Model;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPauseAction implements ActionListener {

    private final Model model;
    private final View view;

    public PlayPauseAction(Model model, View view){
        this.model = model;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("PlayPause pressed.");
        this.model.playPause();
        // TODO
        this.view.updateView(this.model.updateView());
    }
}
