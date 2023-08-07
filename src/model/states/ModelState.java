package model.states;

import controller.ViewUpdatePacket;
import model.Model;
import model.generators.Generator;
import model.solvers.Solver;

public abstract class ModelState {

    protected Model model;
    protected Class<? extends Generator> selectedGenerationAlgo;
    protected Class<? extends Solver> selectedSolvingAlgo;

    public ModelState(Model model) {
        this.model = model;
    }


    public void regenerateMaze() {
        this.model.setState(new GenerateState(this.model, this.selectedGenerationAlgo, this.selectedSolvingAlgo));
    }

    public void changeSelectedGenerationAlgo(Class<? extends Generator> g) {
        this.selectedGenerationAlgo = g;
    }

    public void changeSelectedSolvingAlgo(Class<? extends Solver> s) {
        this.selectedSolvingAlgo = s;
    }

    public abstract ViewUpdatePacket updateView();

    public abstract void clearMaze();

    public abstract void step();

    public abstract void finish();

    public abstract void generate();

    public abstract void solve();
}
