package view;

import controller.TileUpdate;
import controller.ViewUpdatePacket;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    final static int MAZE_PANEL_SIZE = 480; // Maze GUI will be 480x480 pixels

    final static int MENU_PANEL_SIZE = 120;
    final static int MAZE_LENGTH = 16; // Number of cells in a row in the square maze
    final static int CELL_LENGTH = MAZE_PANEL_SIZE / MAZE_LENGTH;

    private MenuPanel menuPanel;
    private MazePanel mazePanel;

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public MazePanel getMazePanel() {
        return mazePanel;
    }

//    private String currentMazeGeneratorAlgorithm = "A";

    public View()
    {
        this.setResizable(false);
        this.setTitle("Maze-Solving Simulations");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(MENU_PANEL_SIZE + MAZE_PANEL_SIZE, MAZE_PANEL_SIZE + 8);
        this.setLayout(new BorderLayout());


        this.menuPanel = new MenuPanel(MENU_PANEL_SIZE, MAZE_PANEL_SIZE);
        this.add(this.menuPanel, BorderLayout.WEST);
        this.mazePanel = new MazePanel(MAZE_LENGTH, CELL_LENGTH, MENU_PANEL_SIZE, MAZE_PANEL_SIZE);
        this.add(this.mazePanel);

        this.setVisible(true);
    }

    public void updateView(ViewUpdatePacket packet){
        for (TileUpdate subPacket : packet.tileUpdates()){
            updateTile(subPacket);
        }
        this.getMazePanel().revalidate();
    }

    public void updateTile(TileUpdate update){
        Tile tile = this.getMazePanel().getTile(update.xPos(), update.yPos());
        tile.setTileBorders(update.hasTopBorder(), update.hasRightBorder(), update.hasBottomBorder(), update.hasLeftBorder());
        tile.setHasMouse(update.hasMouse());
        tile.setInitialized(update.initialized());
        tile.setTraversed(update.traversed());
        tile.setHighlighted(update.highlighted());
        tile.setCurrent(update.current());
        tile.repaint();
    }



//    @Override
//    public void actionPerformed(ActionEvent e)
//    {
//        if (e.getSource() == this.menuPanel.getGenerateMazeButton())
//        {
//            System.out.println("Generating a maze with the " + this.currentMazeGeneratorAlgorithm + " algorithm.");
////            this.disableMazeGenerationUI(5000);
//        }
//        else if (e.getSource() == this.menuPanel.getMazeGeneratorComboBox())
//        {
//            this.currentMazeGeneratorAlgorithm = (String) this.menuPanel.getMazeGeneratorComboBox().getSelectedItem();
//        }
//    }

//    public static void pause(int milliseconds)
//    {
//        try {
//            Thread.sleep(milliseconds);
//        } catch (Exception e) {
//        };
//    }

//    public void disableMazeGenerationUI(int milliseconds)
//    {
//        this.menuPanel.getGenerateMazeButton().setEnabled(false);
//        this.menuPanel.getMazeGeneratorComboBox().setEnabled(false);
//
//        pause(milliseconds);
//
//        this.menuPanel.getGenerateMazeButton().setEnabled(true);
//        this.menuPanel.getMazeGeneratorComboBox().setEnabled(true);
//    }
}
