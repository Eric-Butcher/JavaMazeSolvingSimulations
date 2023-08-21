package model.states;

import controller.ViewUpdatePacket;
import model.Grid;
import model.Model;
import model.generators.Generator;
import model.generators.GeneratorAlgorithms;
import model.solvers.Solver;
import model.solvers.SolverAlgorithms;

import java.lang.reflect.InvocationTargetException;

public class SolveState extends ModelState {

    private Generator generatorAlgo;
    private Solver solverAlgo;

    private Grid blankGeneratedGrid;

    public Generator getGeneratorAlgo() {
        return generatorAlgo;
    }

    public void setGeneratorAlgo(Generator generatorAlgo) {
        this.generatorAlgo = generatorAlgo;
    }

    public Solver getSolverAlgo() {
        return solverAlgo;
    }

    public void setSolverAlgo(Solver solverAlgo) {
        this.solverAlgo = solverAlgo;
    }

    //This constructor should only be used for testing
    public SolveState(Model model){
        super(model);
        this.selectedGenerationAlgo = GeneratorAlgorithms.Prim.getClazz();
        this.selectedSolvingAlgo = SolverAlgorithms.HeuristicDepthFirstSearch.getClazz();

    }

    public SolveState(Model model, Class<? extends Generator> selectedGenerationAlgo, Class<? extends Solver> selectedSolvingAlgo, Grid blankGeneratedGrid){
        super(model);
        this.selectedGenerationAlgo = selectedGenerationAlgo;
        this.selectedSolvingAlgo = selectedSolvingAlgo;
        this.blankGeneratedGrid = blankGeneratedGrid;
        this.solve();

    }

    public ViewUpdatePacket updateView(){
        return this.getSolverAlgo().makeViewUpdatePacket();
    }

    public void clearMaze(){
        System.out.println("New solver algorithm put in!");
    }

    public void step(){
        this.getSolverAlgo().iterate();
    }

    public void finish(){
        this.getSolverAlgo().finish();
    }

    public void generate(){
        this.model.setState(new GenerateState(this.model, this.selectedGenerationAlgo, this.selectedSolvingAlgo));
        return;
    }

    public void solve(){
        try {
            this.blankGeneratedGrid.unSolveGrid();
            this.setSolverAlgo(this.selectedSolvingAlgo.getDeclaredConstructor(Grid.class).newInstance(this.blankGeneratedGrid));
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
