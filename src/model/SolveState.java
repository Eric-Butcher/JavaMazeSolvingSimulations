package model;

import controller.ViewUpdatePacket;
import model.generators.Generator;
import model.solvers.Solver;
import utilities.Constants;

public class SolveState extends ModelState{

    private Generator generatorAlgo;
    private Solver solverAlgo;

    public Generator getGeneratorAlgo() {
        return generatorAlgo;
    }

    public void setGeneratorAlgo(Generator generatorAlgo) {
        this.generatorAlgo = generatorAlgo;
    }

    public SolveState(Model model){
        super(model);
    }

    public ViewUpdatePacket updateView(){
        return Constants.emptyViewUpdatePacket;
    }

    public void clearMaze(){
    }

    public void step(){
    }

    public void playPause(){

    }

    public void generate(){

    }

    public void solve(){

    }
}
