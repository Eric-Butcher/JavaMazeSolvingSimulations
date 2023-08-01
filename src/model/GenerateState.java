package model;

import model.generators.Generator;

import java.lang.reflect.InvocationTargetException;

public class GenerateState extends ModelState{

    private Generator generatorAlgo;

    public GenerateState(Model model){
        super(model);
    }

    public void clearMaze(){
    }

    public void step(){

    }

    public void playPause(){

    }

    public void generate(){
        try {
            this.generatorAlgo = this.selectedGenerationAlgo.getDeclaredConstructor().newInstance();
            System.out.println(this.generatorAlgo);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void solve(){

    }
}
