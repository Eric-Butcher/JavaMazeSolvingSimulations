package model;

import model.generators.Generator;
import model.solvers.Solver;

public class Model {

    private ModelState modelState;

    public Model(){
        this.modelState = new DefaultState(this);
    }

    public void setState(ModelState modelState){
        this.modelState = modelState;
    }

    public void resetMaze() {
        this.modelState.resetMaze();
    }

    public void changeSelectedGenerationAlgo(Class<Generator> g){
        this.modelState.changeSelectedGenerationAlgo(g);
    }

    public void changeSelectedSolvingAlgo(Class<Solver> s){
        this.modelState.changeSelectedSolvingAlgo(s);
    }

    public void clearMaze(){
        this.modelState.clearMaze();
    }

    public void step(){
        this.modelState.step();
    }

    public void playPause(){
        this.modelState.playPause();
    }

    public void generate() {
        this.modelState.generate();
    }

    public void solve(){
        this.modelState.solve();
    }

}
