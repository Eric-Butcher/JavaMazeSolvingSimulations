package tests;

import model.Cell;
import model.Grid;
import org.junit.jupiter.api.Test;
import utilities.Constants;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    public void testGridConstrcutor(){
        Grid grid = new Grid();

        for (int x = Constants.minCellIndex; x < Constants.mazeLength; x++){
            for (int y = Constants.minCellIndex; y < Constants.mazeLength; y++){

                assertTrue(grid.getCell(x, y).isBottomBorder());
                assertTrue(grid.getCell(x, y).isLeftBorder());
                assertTrue(grid.getCell(x, y).isTopBorder());
                assertTrue(grid.getCell(x, y).isRightBorder());

            }
        }
    }

    @Test
    public void testGetRandomGridCell(){
        Grid grid = new Grid();

        for (int i = 0; i < 300; i++){
            Cell cell = grid.getRandomGridCell();
            assertTrue(cell.getxPos() >= Constants.minCellIndex);
            assertTrue(cell.getxPos() <= Constants.maxCellIndex);
            assertTrue(cell.getyPos() >= Constants.minCellIndex);
            assertTrue(cell.getyPos() <= Constants.maxCellIndex);
        }
    }

    @Test
    public void testCreatePathBetweenCells(){
        Grid grid = new Grid();

        Cell center = grid.getCell(5, 5);
        assertTrue(center.isBottomBorder());
        assertTrue(center.isLeftBorder());
        assertTrue(center.isTopBorder());
        assertTrue(center.isRightBorder());

        Cell top = grid.getCell(5, 4);
        assertTrue(top.isBottomBorder());
        assertTrue(top.isLeftBorder());
        assertTrue(top.isTopBorder());
        assertTrue(top.isRightBorder());

        Cell right = grid.getCell(6, 5);
        assertTrue(right.isBottomBorder());
        assertTrue(right.isLeftBorder());
        assertTrue(right.isTopBorder());
        assertTrue(right.isRightBorder());

        Cell under = grid.getCell(5, 6);
        assertTrue(under.isBottomBorder());
        assertTrue(under.isLeftBorder());
        assertTrue(under.isTopBorder());
        assertTrue(under.isRightBorder());

        Cell left = grid.getCell(4, 5);
        assertTrue(left.isBottomBorder());
        assertTrue(left.isLeftBorder());
        assertTrue(left.isTopBorder());
        assertTrue(left.isRightBorder());

        grid.createPathBetweenCells(center, top);

        assertTrue(center.isBottomBorder());
        assertTrue(center.isLeftBorder());
        assertFalse(center.isTopBorder());
        assertTrue(center.isRightBorder());
        assertFalse(top.isBottomBorder());
        assertTrue(top.isLeftBorder());
        assertTrue(top.isTopBorder());
        assertTrue(top.isRightBorder());

        grid.createPathBetweenCells(center, right);

        assertTrue(center.isBottomBorder());
        assertTrue(center.isLeftBorder());
        assertFalse(center.isTopBorder());
        assertFalse(center.isRightBorder());
        assertTrue(right.isBottomBorder());
        assertFalse(right.isLeftBorder());
        assertTrue(right.isTopBorder());
        assertTrue(right.isRightBorder());

        grid.createPathBetweenCells(center, under);

        assertFalse(center.isBottomBorder());
        assertTrue(center.isLeftBorder());
        assertFalse(center.isTopBorder());
        assertFalse(center.isRightBorder());
        assertTrue(under.isBottomBorder());
        assertTrue(under.isLeftBorder());
        assertFalse(under.isTopBorder());
        assertTrue(under.isRightBorder());

        grid.createPathBetweenCells(center, left);

        assertFalse(center.isBottomBorder());
        assertFalse(center.isLeftBorder());
        assertFalse(center.isTopBorder());
        assertFalse(center.isRightBorder());
        assertTrue(left.isBottomBorder());
        assertTrue(left.isLeftBorder());
        assertTrue(left.isTopBorder());
        assertFalse(left.isRightBorder());

    }

    @Test
    public void testGetAdjacentCells(){
        Grid grid = new Grid();

        int cornerSize = 2;
        int sideSize = 3;
        int middleSize = 4;

        //Top left corner
        ArrayList<Cell> a = grid.getAdjacentCells(grid.getCell(Constants.minCellIndex, Constants.minCellIndex));
        assertEquals(cornerSize, a.size());
        assertTrue(a.contains(grid.getCell(Constants.minCellIndex, Constants.minCellIndex+1)));
        assertTrue(a.contains(grid.getCell(Constants.minCellIndex+1, Constants.minCellIndex)));

        //Top right corner
        ArrayList<Cell> b = grid.getAdjacentCells(grid.getCell(Constants.maxCellIndex, Constants.minCellIndex));
        assertEquals(cornerSize, b.size());
        assertTrue(b.contains(grid.getCell(Constants.maxCellIndex-1, Constants.minCellIndex)));
        assertTrue(b.contains(grid.getCell(Constants.maxCellIndex, Constants.minCellIndex+1)));

        //Bottom left corner
        ArrayList<Cell> c = grid.getAdjacentCells(grid.getCell(Constants.minCellIndex, Constants.maxCellIndex));
        assertEquals(cornerSize, c.size());
        assertTrue(c.contains(grid.getCell(Constants.minCellIndex+1, Constants.maxCellIndex)));
        assertTrue(c.contains(grid.getCell(Constants.minCellIndex, Constants.maxCellIndex-1)));

        //Bottom right corner
        ArrayList<Cell> d = grid.getAdjacentCells(grid.getCell(Constants.maxCellIndex, Constants.maxCellIndex));
        assertEquals(cornerSize, d.size());
        assertTrue(d.contains(grid.getCell(Constants.maxCellIndex-1, Constants.maxCellIndex)));
        assertTrue(d.contains(grid.getCell(Constants.maxCellIndex, Constants.maxCellIndex-1)));

        //Top side
        int middleCord = 7;
        ArrayList<Cell> e = grid.getAdjacentCells(grid.getCell(middleCord, Constants.minCellIndex));
        assertEquals(sideSize, e.size());
        assertTrue(e.contains(grid.getCell(middleCord-1, Constants.minCellIndex)));
        assertTrue(e.contains(grid.getCell(middleCord+1, Constants.minCellIndex)));
        assertTrue(e.contains(grid.getCell(middleCord, Constants.minCellIndex+1)));

        //Right side
        middleCord = 9;
        ArrayList<Cell> f = grid.getAdjacentCells(grid.getCell(Constants.maxCellIndex, middleCord));
        assertEquals(sideSize, f.size());
        assertTrue(f.contains(grid.getCell(Constants.maxCellIndex-1, middleCord)));
        assertTrue(f.contains(grid.getCell(Constants.maxCellIndex, middleCord+1)));
        assertTrue(f.contains(grid.getCell(Constants.maxCellIndex, middleCord-1)));

        //Bottom side
        middleCord = 4;
        ArrayList<Cell> g = grid.getAdjacentCells(grid.getCell(middleCord, Constants.maxCellIndex));
        assertEquals(sideSize, g.size());
        assertTrue(g.contains(grid.getCell(middleCord-1, Constants.maxCellIndex)));
        assertTrue(g.contains(grid.getCell(middleCord+1, Constants.maxCellIndex)));
        assertTrue(g.contains(grid.getCell(middleCord, Constants.maxCellIndex-1)));

        //Left side
        middleCord = 12;
        ArrayList<Cell> h = grid.getAdjacentCells(grid.getCell(Constants.minCellIndex, middleCord));
        assertEquals(sideSize, h.size());
        assertTrue(h.contains(grid.getCell(Constants.minCellIndex+1, middleCord)));
        assertTrue(h.contains(grid.getCell(Constants.minCellIndex, middleCord-1)));
        assertTrue(h.contains(grid.getCell(Constants.minCellIndex, middleCord+1)));

        // Somewhere in the middle
        middleCord = 3;
        ArrayList<Cell> i = grid.getAdjacentCells(grid.getCell(middleCord, middleCord));
        assertEquals(middleSize, i.size());
        assertTrue(i.contains(grid.getCell(middleCord+1, middleCord)));
        assertTrue(i.contains(grid.getCell(middleCord, middleCord+1)));
        assertTrue(i.contains(grid.getCell(middleCord-1, middleCord)));
        assertTrue(i.contains(grid.getCell(middleCord, middleCord-1)));

        // Somewhere else in the middle
        middleCord = 9;
        int otherMiddleCord = 14;
        ArrayList<Cell> j = grid.getAdjacentCells(grid.getCell(middleCord, otherMiddleCord));
        assertEquals(middleSize, j.size());
        assertTrue(j.contains(grid.getCell(middleCord-1, otherMiddleCord)));
        assertTrue(j.contains(grid.getCell(middleCord, otherMiddleCord-1)));
        assertTrue(j.contains(grid.getCell(middleCord+1, otherMiddleCord)));
        assertTrue(j.contains(grid.getCell(middleCord, otherMiddleCord+1)));
    }

    @Test
    public void testIsTherePathBetweenCells(){
        Cell a1 = new Cell(0, 1);
        Cell a2 = new Cell(1, 1);
        assertFalse(Grid.isTherePathBetweenCells(a1, a2));
        assertFalse(Grid.isTherePathBetweenCells(a2, a1));

        // To the right/left
        Cell b1 = new Cell(0, 1);
        b1.removeRightBorder();
        Cell b2 = new Cell(1, 1);
        b2.removeLeftBorder();
        assertTrue(Grid.isTherePathBetweenCells(b1, b2));
        assertTrue(Grid.isTherePathBetweenCells(b2, b1));

        // To the top/bottom
        Cell c1 = new Cell(4, 4);
        c1.removeBottomBorder();
        Cell c2 = new Cell(4, 5);
        c2.removeTopBorder();
        assertTrue(Grid.isTherePathBetweenCells(c1, c2));
        assertTrue(Grid.isTherePathBetweenCells(c2, c1));

        // If cells are too far apart
        Cell d1 = new Cell(0, 0);
        d1.removeBottomBorder();
        Cell d2 = new Cell(0, 2);
        d2.removeTopBorder();
        assertFalse(Grid.isTherePathBetweenCells(d1, d2));
        assertFalse(Grid.isTherePathBetweenCells(d2, d1));

        // If cells are on a diagonal
        Cell e1 = new Cell(4, 4);
        e1.removeBottomBorder();
        e1.removeRightBorder();
        Cell e2 = new Cell(5, 5);
        e2.removeTopBorder();
        e2.removeLeftBorder();
        assertFalse(Grid.isTherePathBetweenCells(e1, e2));
        assertFalse(Grid.isTherePathBetweenCells(e2, e1));

    }
}
