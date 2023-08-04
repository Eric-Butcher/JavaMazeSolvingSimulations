package model;

import controller.ViewUpdatePacket;
import model.generators.Generator;
import model.generators.GeneratorAlgorithms;
import model.solvers.Solver;
import model.solvers.SolverAlgorithms;

import java.lang.reflect.InvocationTargetException;

public class GenerateState extends ModelState{

    private Generator generatorAlgo;

    public Generator getGeneratorAlgo() {
        return generatorAlgo;
    }

    public void setGeneratorAlgo(Generator generatorAlgo) {
        this.generatorAlgo = generatorAlgo;
    }

    public GenerateState(Model model){
        super(model);
        this.selectedGenerationAlgo = GeneratorAlgorithms.Prim.getClazz();
        this.selectedSolvingAlgo = SolverAlgorithms.BFS.getClazz();
    }

    public GenerateState(Model model, Class<? extends Generator> selectedGenerationAlgo, Class<? extends Solver> selectedSolvingAlgo){
        super(model);
        this.selectedGenerationAlgo = selectedGenerationAlgo;
        this.selectedSolvingAlgo = selectedSolvingAlgo;
    }

    public ViewUpdatePacket updateView(){
        return this.getGeneratorAlgo().makeViewUpdatePacket();
    }

    public void clearMaze(){
        return;
    }

    public void step(){
        this.getGeneratorAlgo().iterate();
    }

    public void playPause(){
        //TODO
        this.getGeneratorAlgo().finish();
        return;
    }

    public void generate(){
        try {
            this.generatorAlgo = this.selectedGenerationAlgo.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void solve(){
        // TODO
        return;
    }
}
