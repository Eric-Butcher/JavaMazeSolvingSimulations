package model;

public abstract class ModelState {

    protected Model model;

    public ModelState(Model model)
    {
        this.model = model;
    }

    public void resetMaze()
    {

    }

    public abstract void clearMaze();

    public abstract void step();

    public abstract void playPause();

    public abstract void generate();

    public abstract void solve();
}
