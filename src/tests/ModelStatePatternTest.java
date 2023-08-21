package tests;

import model.states.GenerateState;
import model.Model;
import model.states.SolveState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelStatePatternTest {

    @Test
    public void ModelState_GenerateState_Step() {
        Model model = new Model();
        model.setState(new GenerateState(model));

        assertTrue(model.getModelState() instanceof GenerateState);

        model.step();

        assertTrue(model.getModelState() instanceof GenerateState);
    }

    @Test
    public void ModelState_GenerateState_Finish() {
        Model model = new Model();
        model.setState(new GenerateState(model));

        assertTrue(model.getModelState() instanceof GenerateState);

        model.finish();

        assertTrue(model.getModelState() instanceof GenerateState);
    }

    @Test
    public void ModelState_GenerateState_Solve() {

        // Maze not done
        Model model = new Model();
        model.setState(new GenerateState(model));

        assertTrue(model.getModelState() instanceof GenerateState);

        model.solve();

        assertTrue(model.getModelState() instanceof GenerateState);

        model.generate();

        ((GenerateState) model.getModelState()).getGeneratorAlgo().finish();

        model.solve();

        assertTrue(model.getModelState() instanceof SolveState);
    }

    @Test
    public void ModelState_GenerateState_Generate() {
        Model model = new Model();
        model.setState(new GenerateState(model));

        assertTrue(model.getModelState() instanceof GenerateState);

        model.solve();

        assertTrue(model.getModelState() instanceof GenerateState);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void ModelState_SolveState_Step() {
        Model model = new Model();
        model.setState(new SolveState(model));

        assertTrue(model.getModelState() instanceof SolveState);

        model.step();

        assertTrue(model.getModelState() instanceof SolveState);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void ModelState_SolveState_Finish() {
        Model model = new Model();
        model.setState(new SolveState(model));

        assertTrue(model.getModelState() instanceof SolveState);

        model.finish();

        assertTrue(model.getModelState() instanceof SolveState);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void ModelState_SolveState_Solve() {
        Model model = new Model();
        model.setState(new SolveState(model));

        assertTrue(model.getModelState() instanceof SolveState);

        model.solve();

        assertTrue(model.getModelState() instanceof SolveState);
    }

}
