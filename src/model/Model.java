package model;

public class Model {

    private ModelState modelState;

    public Model(){
        this.modelState = new DefaultState(this);
    }

    public void setState(ModelState modelState){
        this.modelState = modelState;
    }

    public void resetMaze() {
        this.modelState.resetMaze();
    }

    public void changeGenerationAlgo(String s){
        this.modelState.changeGenerationAlgo(s);
    }

    public void changeSolvingAlgo(String s){
        this.modelState.changeSolvingAlgo(s);
    }

    public void clearMaze(){
        this.modelState.clearMaze();
    }

    public void step(){
        this.modelState.step();
    }

    public void playPause(){
        this.modelState.playPause();
    }

    public void generate() {
        this.modelState.generate();
    }

    public void solve(){
        this.modelState.solve();
    }

}
