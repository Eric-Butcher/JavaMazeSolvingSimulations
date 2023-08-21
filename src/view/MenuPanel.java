package view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private GenerateMazeButton generateMazeButton;
    private MazeGeneratorComboBox mazeGeneratorComboBox;
//    private PlayPauseButton playPauseButton;

    private FinishButton finishButton;
    private StepButton stepButton;
//    private ClearButton clearButton;
//    private RegenerateButton regenerateButton;
    private SolveMazeButton solveMazeButton;
    private MazeSolverComboBox mazeSolverComboBox;

    public MenuPanel(int menuLength, int menuHeight) {
        // Panel setup
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(menuLength, menuHeight));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // Create components
        this.generateMazeButton = new GenerateMazeButton();
        this.mazeGeneratorComboBox = new MazeGeneratorComboBox();
        this.solveMazeButton = new SolveMazeButton();
        this.mazeSolverComboBox = new MazeSolverComboBox();
//        this.playPauseButton = new PlayPauseButton();
        this.finishButton = new FinishButton();
        this.stepButton = new StepButton();
//        this.regenerateButton = new RegenerateButton();
//        this.clearButton = new ClearButton();


        // Descriptive variables
        int firstColumn = 0, secondColumn = 1;

        int firstRow = 0, secondRow = 1, thirdRow = 2, fourthRow = 3, fifthRow = 4, sixthRow = 5, seventhRow = 6, eighthRow = 7;

        // Add components and place them in correct positions with gridBagConstraints

        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridx = firstColumn;
        gridBagConstraints.gridy = firstRow;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.generateMazeButton, gridBagConstraints);

        gridBagConstraints.gridx = firstColumn;
        gridBagConstraints.gridy = secondRow;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.mazeGeneratorComboBox, gridBagConstraints);

        gridBagConstraints.gridx = firstColumn;
        gridBagConstraints.gridy = thirdRow;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.solveMazeButton, gridBagConstraints);

        gridBagConstraints.gridx = firstColumn;
        gridBagConstraints.gridy = fourthRow;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.mazeSolverComboBox, gridBagConstraints);


        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridx = firstColumn;
        gridBagConstraints.gridy = fifthRow;
//        this.add(this.playPauseButton, gridBagConstraints);
        this.add(this.finishButton, gridBagConstraints);

        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridx = firstColumn;
        gridBagConstraints.gridy = sixthRow;
        this.add(this.stepButton, gridBagConstraints);

//        gridBagConstraints.gridwidth = 0;
//        gridBagConstraints.gridx = firstColumn;
//        gridBagConstraints.gridy = seventhRow;
//        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
//        this.add(this.regenerateButton, gridBagConstraints);
//
//
//        gridBagConstraints.gridx = firstColumn;
//        gridBagConstraints.gridy = eighthRow;
//        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
//        this.add(this.clearButton, gridBagConstraints);

        this.setVisible(true);

    }

    public GenerateMazeButton getGenerateMazeButton() {
        return generateMazeButton;
    }

    public MazeGeneratorComboBox getMazeGeneratorComboBox() {
        return mazeGeneratorComboBox;
    }

//    public PlayPauseButton getPlayPauseButton() {
//        return playPauseButton;
//    }

    public FinishButton getFinishButton() {
        return finishButton;
    }

    public StepButton getStepButton() {
        return stepButton;
    }

//    public ClearButton getClearButton() {
//        return clearButton;
//    }
//
//    public RegenerateButton getRegenerateButton() {
//        return regenerateButton;
//    }

    public SolveMazeButton getSolveMazeButton() {
        return solveMazeButton;
    }

    public MazeSolverComboBox getMazeSolverComboBox() {
        return mazeSolverComboBox;
    }
}
