package model.states;

import controller.ViewUpdatePacket;
import model.Model;
import model.generators.Generator;
import model.generators.GeneratorAlgorithms;
import model.solvers.Solver;
import model.solvers.SolverAlgorithms;
import utilities.Constants;

public class SolveState extends ModelState {

    private Generator generatorAlgo;
    private Solver solverAlgo;

    public Generator getGeneratorAlgo() {
        return generatorAlgo;
    }

    public void setGeneratorAlgo(Generator generatorAlgo) {
        this.generatorAlgo = generatorAlgo;
    }

    //This constructor should only be used for testing
    public SolveState(Model model){
        super(model);
        this.selectedGenerationAlgo = GeneratorAlgorithms.Prim.getClazz();
        this.selectedSolvingAlgo = SolverAlgorithms.BFS.getClazz();
    }

    public SolveState(Model model, Class<? extends Generator> selectedGenerationAlgo, Class<? extends Solver> selectedSolvingAlgo){
        super(model);
        this.selectedGenerationAlgo = selectedGenerationAlgo;
        this.selectedSolvingAlgo = selectedSolvingAlgo;

    }

    public ViewUpdatePacket updateView(){
        return Constants.emptyViewUpdatePacket;
    }

    public void clearMaze(){
        System.out.println("New solver algorithm put in!");
    }

    public void step(){
        System.out.println("I stepped!");
    }

    public void playPause(){
        System.out.println("Should be soled!");
    }

    public void generate(){
        this.model.setState(new GenerateState(this.model, this.selectedGenerationAlgo, this.selectedSolvingAlgo));
        return;
    }

    public void solve(){
        this.model.setState(new SolveState(this.model, this.selectedGenerationAlgo, this.selectedSolvingAlgo));
    }
}
