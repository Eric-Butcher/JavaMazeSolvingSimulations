package controller;

import model.Model;
import view.View;

public class Controller {

    private final Model model;
    private final View view;

    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;

        this.view.getMenuPanel().getGenerateMazeButton().addActionListener(new GenerateAction(this.model));
        this.view.getMenuPanel().getMazeGeneratorComboBox().addActionListener(new GenerateSelectAction(this.model));
        this.view.getMenuPanel().getClearButton().addActionListener(new ClearAction(this.model));
        this.view.getMenuPanel().getResetButton().addActionListener(new ResetAction(this.model));
        this.view.getMenuPanel().getPlayPauseButton().addActionListener(new PlayPauseAction(this.model));
        this.view.getMenuPanel().getMazeSolverComboBox().addActionListener(new SolverSelectAction(this.model));
        this.view.getMenuPanel().getSolveMazeButton().addActionListener(new SolveAction(this.model));

    }

}
