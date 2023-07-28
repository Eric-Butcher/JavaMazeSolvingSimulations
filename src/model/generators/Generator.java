package model.generators;

public abstract class Generator {

    private Cell[][] grid = new Cell[16][16];

    public Generator(){

        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 16; j++){
                Cell cell = new Cell(i, j);
                this.grid[i][j] = cell;
            }
        }
    }

    public void iterate(){}


}
