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

        this.view.getMenuPanel().getGenerateMazeButton().addActionListener(new GenerateAction(this.model, this.view));
        this.view.getMenuPanel().getMazeGeneratorComboBox().addActionListener(new GenerateSelectAction(this.model, this.view));

        this.view.getMenuPanel().getSolveMazeButton().addActionListener(new SolveAction(this.model, this.view));
        this.view.getMenuPanel().getMazeSolverComboBox().addActionListener(new SolverSelectAction(this.model, this.view));

        this.view.getMenuPanel().getFinishButton().addActionListener(new FinishAction(this.model, this.view));
        this.view.getMenuPanel().getStepButton().addActionListener(new StepAction(this.model, this.view));
    }

}
