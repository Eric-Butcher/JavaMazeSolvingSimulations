package model;

public abstract class ModelState {

    protected Model model;
    protected String generationAlgo;
    protected String solvingAlgo;

    public ModelState(Model model)
    {
        this.model = model;
    }

    public void resetMaze()
    {

    }

    public void changeGenerationAlgo(String s){
        this.generationAlgo = s;
        System.out.println(s);
    }

    public void changeSolvingAlgo(String s){
        this.solvingAlgo = s;
        System.out.println(s);
    }

    public abstract void clearMaze();

    public abstract void step();

    public abstract void playPause();

    public abstract void generate();

    public abstract void solve();
}
