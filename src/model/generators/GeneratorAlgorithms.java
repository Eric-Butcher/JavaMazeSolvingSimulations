package model.generators;

public enum GeneratorAlgorithms {
    Prim(PrimGenerator.class),
    AldousBroder(AldousBroderGenerator.class);
    private final Class<? extends Generator> clazz;

    GeneratorAlgorithms(Class<? extends Generator> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Generator> getClazz() {
        return clazz;
    }
}

