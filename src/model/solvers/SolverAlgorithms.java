package model.solvers;

public enum SolverAlgorithms {


    HeuristicDepthFirstSearch(HeuristicDepthFirstSearchSolver.class);

    private final Class<? extends Solver> clazz;

    SolverAlgorithms(Class<? extends Solver> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Solver> getClazz() {
        return clazz;
    }

}
