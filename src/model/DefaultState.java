package model;

import model.generators.GeneratorAlgorithms;
import model.solvers.SolverAlgorithms;

public class DefaultState extends ModelState{

    public DefaultState(Model model){
        super(model);
        this.selectedGenerationAlgo = GeneratorAlgorithms.Prim.getClazz();
        this.selectedSolvingAlgo = SolverAlgorithms.BFS.getClazz();
    }

    public void clearMaze(){
        return;
    }

    public void step(){
        return;
    }

    public void playPause(){
        return;
    }

    public void generate(){
        this.model.setState(new GenerateState(this.model));
    }


    public void solve(){
        return;
    }
}
