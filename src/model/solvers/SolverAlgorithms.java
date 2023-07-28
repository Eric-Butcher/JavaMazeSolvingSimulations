package model.solvers;

import model.generators.DummyGenerator;
import model.generators.Generator;
import model.generators.PrimGenerator;

public enum SolverAlgorithms {

    DUMMY(DummySolver.class),
    BFS(BFSSolver.class);

    private final Class<? extends Solver> clazz;

    SolverAlgorithms(Class<? extends Solver> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Solver> getClazz() {
        return clazz;
    }

}
