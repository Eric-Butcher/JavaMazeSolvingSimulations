package model;

import controller.ViewUpdatePacket;
import model.generators.Generator;
import model.solvers.Solver;
import model.states.GenerateState;
import model.states.ModelState;

public class Model {

    private ModelState modelState;

    public Model() {
        this.modelState = new GenerateState(this);
    }

    public void setState(ModelState modelState) {
        this.modelState = modelState;
    }

    public ViewUpdatePacket updateView() {
        return this.modelState.updateView();
    }

    public ModelState getModelState() {
        return modelState;
    }

    public void regenerateMaze() {
        this.modelState.regenerateMaze();
    }

    public void changeSelectedGenerationAlgo(Class<Generator> g) {
        this.modelState.changeSelectedGenerationAlgo(g);
    }

    public void changeSelectedSolvingAlgo(Class<Solver> s) {
        this.modelState.changeSelectedSolvingAlgo(s);
    }

    public void clearMaze() {
        this.modelState.clearMaze();
    }

    public void step() {
        this.modelState.step();
    }

    public void playPause() {
        this.modelState.playPause();
    }

    public void generate() {
        this.modelState.generate();
    }

    public void solve() {
        this.modelState.solve();
    }

}
