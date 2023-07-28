package model.generators;

public enum GeneratorAlgorithms {
    PRIM(PrimGenerator.class),
    DUMMY(DummyGenerator.class);

    private final Class<? extends Generator> clazz;

    GeneratorAlgorithms(Class<? extends Generator> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Generator> getClazz() {
        return clazz;
    }
}

