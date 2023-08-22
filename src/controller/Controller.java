package controller;

import model.Model;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private final Model model;
    private final View view;
    private final ActionListener autoIterateFromPlayer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.step();
            view.updateView(model.updateView());
        }
    };

    private final int delayInMilliseconds = 100;
    private final Timer timer = new Timer(delayInMilliseconds, autoIterateFromPlayer);

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.view.getMenuPanel().getGenerateMazeButton().addActionListener(new GenerateAction(this.model, this.view, this));
        this.view.getMenuPanel().getMazeGeneratorComboBox().addActionListener(new GenerateSelectAction(this.model, this.view));

        this.view.getMenuPanel().getSolveMazeButton().addActionListener(new SolveAction(this.model, this.view, this));
        this.view.getMenuPanel().getMazeSolverComboBox().addActionListener(new SolverSelectAction(this.model, this.view));

        this.view.getMenuPanel().getFinishButton().addActionListener(new FinishAction(this.model, this.view, this));
        this.view.getMenuPanel().getStepButton().addActionListener(new StepAction(this.model, this.view, this));

        this.view.getMenuPanel().getPlayPauseButton().addActionListener(new PlayPauseAction(this.model, this.view, this));

    }

    public Timer getTimer() {
        return timer;
    }

    public void playPause() {
        if (this.timer.isRunning()) {
            this.timer.stop();
        } else {
            this.timer.restart();
        }
    }

    public void stopPlaying() {
        this.timer.stop();
    }

}
