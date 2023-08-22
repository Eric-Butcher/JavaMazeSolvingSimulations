package tests;

import controller.Controller;
import model.Model;
import org.junit.jupiter.api.Test;
import view.View;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTimerTest {

    @Test
    public void testTimerStoppedOnInitialisation() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        assertFalse(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerRunningAfterPlayPause() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();

        assertTrue(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerStopsAfterSecondPlayPause() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();
        controller.playPause();

        assertFalse(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerStopsAfterStopPlaying() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();
        controller.stopPlaying();

        assertFalse(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerStartsAfterStopPlaying() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();
        controller.stopPlaying();
        controller.playPause();

        assertTrue(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerStopsAfterGenerate() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();
        view.getMenuPanel().getGenerateMazeButton().doClick();

        assertFalse(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerStopsAfterSolve() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();
        view.getMenuPanel().getSolveMazeButton().doClick();

        assertFalse(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerStopsAfterStep() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();
        view.getMenuPanel().getStepButton().doClick();

        assertFalse(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerStopsAfterFinish() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();
        view.getMenuPanel().getFinishButton().doClick();

        assertFalse(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerStillRunningAfterGenerateComboBoxSelected() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();
        view.getMenuPanel().getMazeGeneratorComboBox().setSelectedIndex(0);

        assertTrue(controller.getTimer().isRunning());
    }

    @Test
    public void testTimerStillRunningAfterSolveComboBoxSelected() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.playPause();
        view.getMenuPanel().getMazeSolverComboBox().setSelectedIndex(0);

        assertTrue(controller.getTimer().isRunning());
    }

}
