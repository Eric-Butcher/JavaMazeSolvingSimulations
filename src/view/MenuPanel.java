package view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private GenerateMazeButton generateMazeButton;
    private MazeGeneratorComboBox mazeGeneratorComboBox;
    private PlayPauseButton playPauseButton;
    private StepButton stepButton;
    private ClearButton clearButton;
    private ResetButton resetButton;
    private SolveMazeButton solveMazeButton;
    private MazeSolverComboBox mazeSolverComboBox;

    public MenuPanel(int menuLength, int menuHeight)
    {
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
        this.playPauseButton = new PlayPauseButton();
        this.stepButton = new StepButton();
        this.clearButton = new ClearButton();
        this.resetButton = new ResetButton();

        // Descriptive variables
        int firstColumn = 0, secondColumn = 1;

        int firstRow = 0, secondRow = 1, thirdRow = 2, fourthRow = 3, fifthRow = 4, sixthRow = 5, seventhRow = 6;

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


        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = firstColumn;
        gridBagConstraints.gridy = fifthRow;
        this.add(this.playPauseButton, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = secondColumn;
        gridBagConstraints.gridy = fifthRow;
        this.add(this.stepButton, gridBagConstraints);

        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridx = firstColumn;
        gridBagConstraints.gridy = sixthRow;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.clearButton, gridBagConstraints);

        gridBagConstraints.gridx = firstColumn;
        gridBagConstraints.gridy = seventhRow;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.resetButton, gridBagConstraints);


        this.setVisible(true);

    }
    public GenerateMazeButton getGenerateMazeButton() {
        return generateMazeButton;
    }

    public MazeGeneratorComboBox getMazeGeneratorComboBox() {
        return mazeGeneratorComboBox;
    }

    public PlayPauseButton getPlayPauseButton() {
        return playPauseButton;
    }

    public StepButton getStepButton() {
        return stepButton;
    }

    public ClearButton getClearButton() {
        return clearButton;
    }

    public ResetButton getResetButton() {
        return resetButton;
    }

    public SolveMazeButton getSolveMazeButton() {
        return solveMazeButton;
    }

    public MazeSolverComboBox getMazeSolverComboBox() {
        return mazeSolverComboBox;
    }
}
