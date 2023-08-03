package model;

import controller.ViewUpdatePacket;
import model.generators.Generator;

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
    }

    public ViewUpdatePacket updateView(){
        return this.getGeneratorAlgo().makeViewUpdatePacket();
    }

    public void clearMaze(){
    }

    public void step(){
        System.out.println("stepped");
        this.getGeneratorAlgo().iterate();
    }

    public void playPause(){

    }

    public void generate(){
        try {
            this.generatorAlgo = this.selectedGenerationAlgo.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void solve(){

    }
}
