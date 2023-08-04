package tests;

import controller.TileUpdate;
import model.generators.Cell;
import model.generators.Generator;
import model.generators.PrimGenerator;
import org.junit.jupiter.api.Test;
import utilities.Constants;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {

    @Test
    public void testGeneratorConstructor(){
        PrimGenerator primGenerator = new PrimGenerator();


        for (int x = Constants.minCellIndex; x < Constants.mazeLength; x++){
            for (int y = Constants.minCellIndex; y < Constants.mazeLength; y++){

                assertTrue(primGenerator.getCell(x, y).isBottomBorder());
                assertTrue(primGenerator.getCell(x, y).isLeftBorder());
                assertTrue(primGenerator.getCell(x, y).isTopBorder());
                assertTrue(primGenerator.getCell(x, y).isRightBorder());

            }
        }

    }

    @Test
    public void testGetRandomGridCell(){
        PrimGenerator primGenerator = new PrimGenerator();

        for (int i = 0; i < 300; i++){
            Cell cell = primGenerator.getRandomGridCell();
            assertTrue(cell.getxPos() >= Constants.minCellIndex);
            assertTrue(cell.getxPos() <= Constants.maxCellIndex);
            assertTrue(cell.getyPos() >= Constants.minCellIndex);
            assertTrue(cell.getyPos() <= Constants.maxCellIndex);
        }
    }

    @Test
    public void testClearPathBetweenCells(){
        PrimGenerator primGenerator = new PrimGenerator();

        Cell center = primGenerator.getCell(5, 5);
        assertTrue(center.isBottomBorder());
        assertTrue(center.isLeftBorder());
        assertTrue(center.isTopBorder());
        assertTrue(center.isRightBorder());

        Cell top = primGenerator.getCell(5, 4);
        assertTrue(top.isBottomBorder());
        assertTrue(top.isLeftBorder());
        assertTrue(top.isTopBorder());
        assertTrue(top.isRightBorder());

        Cell right = primGenerator.getCell(6, 5);
        assertTrue(right.isBottomBorder());
        assertTrue(right.isLeftBorder());
        assertTrue(right.isTopBorder());
        assertTrue(right.isRightBorder());

        Cell under = primGenerator.getCell(5, 6);
        assertTrue(under.isBottomBorder());
        assertTrue(under.isLeftBorder());
        assertTrue(under.isTopBorder());
        assertTrue(under.isRightBorder());

        Cell left = primGenerator.getCell(4, 5);
        assertTrue(left.isBottomBorder());
        assertTrue(left.isLeftBorder());
        assertTrue(left.isTopBorder());
        assertTrue(left.isRightBorder());

        primGenerator.clearPathBetweenCells(center, top);

        assertTrue(center.isBottomBorder());
        assertTrue(center.isLeftBorder());
        assertFalse(center.isTopBorder());
        assertTrue(center.isRightBorder());
        assertFalse(top.isBottomBorder());
        assertTrue(top.isLeftBorder());
        assertTrue(top.isTopBorder());
        assertTrue(top.isRightBorder());

        primGenerator.clearPathBetweenCells(center, right);

        assertTrue(center.isBottomBorder());
        assertTrue(center.isLeftBorder());
        assertFalse(center.isTopBorder());
        assertFalse(center.isRightBorder());
        assertTrue(right.isBottomBorder());
        assertFalse(right.isLeftBorder());
        assertTrue(right.isTopBorder());
        assertTrue(right.isRightBorder());

        primGenerator.clearPathBetweenCells(center, under);

        assertFalse(center.isBottomBorder());
        assertTrue(center.isLeftBorder());
        assertFalse(center.isTopBorder());
        assertFalse(center.isRightBorder());
        assertTrue(under.isBottomBorder());
        assertTrue(under.isLeftBorder());
        assertFalse(under.isTopBorder());
        assertTrue(under.isRightBorder());

        primGenerator.clearPathBetweenCells(center, left);

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
    public void testPopRandomCellFromList(){
        ArrayList<Cell> cells = new ArrayList<>();
        Cell myCell = new Cell(1, 1);
        cells.add(myCell);
        Cell retVal = Generator.popRandomCellFromList(cells);
        assertEquals(myCell, retVal);
        assertEquals(0, cells.size());

        ArrayList<Cell> someCells = new ArrayList<>();
        ArrayList<Cell> keyCells = new ArrayList<>();
        int initSize = 20;
        for (int i = 0; i < initSize; i++){
            Cell cell = new Cell(i, i);
            someCells.add(cell);
            keyCells.add(cell);
        }

        int numRemoved = 0;
        while (someCells.size() != 0){
            Cell retCell = Generator.popRandomCellFromList(someCells);
            assertTrue(keyCells.contains(retCell));
            numRemoved++;
            assertEquals(initSize-numRemoved, someCells.size());
        }
    }

    @Test
    public void testGetInitializedCells(){
        ArrayList<Cell> allCells = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            allCells.add(new Cell(i, i));
        }

        assertEquals(new ArrayList<>(), Generator.getInitializedCells(allCells));
        assertEquals(allCells, Generator.getUnInitializedCells(allCells));

        allCells.get(0).initializeCell();

        ArrayList<Cell> keyA1 = new ArrayList<>();
        keyA1.add(allCells.get(0));
        ArrayList<Cell> keyA2 = new ArrayList<>(allCells);
        keyA2.remove(allCells.get(0));

        assertEquals(keyA1, Generator.getInitializedCells(allCells));
        assertEquals(keyA2, Generator.getUnInitializedCells(allCells));

        allCells.get(5).initializeCell();
        allCells.get(6).initializeCell();
        allCells.get(17).initializeCell();

        ArrayList<Cell> keyB1 = new ArrayList<>();
        keyB1.add(allCells.get(0));
        keyB1.add(allCells.get(5));
        keyB1.add(allCells.get(6));
        keyB1.add(allCells.get(17));
        ArrayList<Cell> keyB2 = new ArrayList<>(allCells);
        keyB2.remove(allCells.get(0));
        keyB2.remove(allCells.get(5));
        keyB2.remove(allCells.get(6));
        keyB2.remove(allCells.get(17));

        assertEquals(keyB1, Generator.getInitializedCells(allCells));
        assertEquals(keyB2, Generator.getUnInitializedCells(allCells));

    }

    @Test
    public void testGetAdjacentCells(){
        PrimGenerator primGenerator = new PrimGenerator();

        int cornerSize = 2;
        int sideSize = 3;
        int middleSize = 4;

        //Top left corner
        ArrayList<Cell> a = primGenerator.getAdjacentCells(primGenerator.getCell(Constants.minCellIndex, Constants.minCellIndex));
        assertEquals(cornerSize, a.size());
        assertTrue(a.contains(primGenerator.getCell(Constants.minCellIndex, Constants.minCellIndex+1)));
        assertTrue(a.contains(primGenerator.getCell(Constants.minCellIndex+1, Constants.minCellIndex)));

        //Top right corner
        ArrayList<Cell> b = primGenerator.getAdjacentCells(primGenerator.getCell(Constants.maxCellIndex, Constants.minCellIndex));
        assertEquals(cornerSize, b.size());
        assertTrue(b.contains(primGenerator.getCell(Constants.maxCellIndex-1, Constants.minCellIndex)));
        assertTrue(b.contains(primGenerator.getCell(Constants.maxCellIndex, Constants.minCellIndex+1)));

        //Bottom left corner
        ArrayList<Cell> c = primGenerator.getAdjacentCells(primGenerator.getCell(Constants.minCellIndex, Constants.maxCellIndex));
        assertEquals(cornerSize, c.size());
        assertTrue(c.contains(primGenerator.getCell(Constants.minCellIndex+1, Constants.maxCellIndex)));
        assertTrue(c.contains(primGenerator.getCell(Constants.minCellIndex, Constants.maxCellIndex-1)));

        //Bottom right corner
        ArrayList<Cell> d = primGenerator.getAdjacentCells(primGenerator.getCell(Constants.maxCellIndex, Constants.maxCellIndex));
        assertEquals(cornerSize, d.size());
        assertTrue(d.contains(primGenerator.getCell(Constants.maxCellIndex-1, Constants.maxCellIndex)));
        assertTrue(d.contains(primGenerator.getCell(Constants.maxCellIndex, Constants.maxCellIndex-1)));

        //Top side
        int middleCord = 7;
        ArrayList<Cell> e = primGenerator.getAdjacentCells(primGenerator.getCell(middleCord, Constants.minCellIndex));
        assertEquals(sideSize, e.size());
        assertTrue(e.contains(primGenerator.getCell(middleCord-1, Constants.minCellIndex)));
        assertTrue(e.contains(primGenerator.getCell(middleCord+1, Constants.minCellIndex)));
        assertTrue(e.contains(primGenerator.getCell(middleCord, Constants.minCellIndex+1)));

        //Right side
        middleCord = 9;
        ArrayList<Cell> f = primGenerator.getAdjacentCells(primGenerator.getCell(Constants.maxCellIndex, middleCord));
        assertEquals(sideSize, f.size());
        assertTrue(f.contains(primGenerator.getCell(Constants.maxCellIndex-1, middleCord)));
        assertTrue(f.contains(primGenerator.getCell(Constants.maxCellIndex, middleCord+1)));
        assertTrue(f.contains(primGenerator.getCell(Constants.maxCellIndex, middleCord-1)));

        //Bottom side
        middleCord = 4;
        ArrayList<Cell> g = primGenerator.getAdjacentCells(primGenerator.getCell(middleCord, Constants.maxCellIndex));
        assertEquals(sideSize, g.size());
        assertTrue(g.contains(primGenerator.getCell(middleCord-1, Constants.maxCellIndex)));
        assertTrue(g.contains(primGenerator.getCell(middleCord+1, Constants.maxCellIndex)));
        assertTrue(g.contains(primGenerator.getCell(middleCord, Constants.maxCellIndex-1)));

        //Left side
        middleCord = 12;
        ArrayList<Cell> h = primGenerator.getAdjacentCells(primGenerator.getCell(Constants.minCellIndex, middleCord));
        assertEquals(sideSize, h.size());
        assertTrue(h.contains(primGenerator.getCell(Constants.minCellIndex+1, middleCord)));
        assertTrue(h.contains(primGenerator.getCell(Constants.minCellIndex, middleCord-1)));
        assertTrue(h.contains(primGenerator.getCell(Constants.minCellIndex, middleCord+1)));

        // Somewhere in the middle
        middleCord = 3;
        ArrayList<Cell> i = primGenerator.getAdjacentCells(primGenerator.getCell(middleCord, middleCord));
        assertEquals(middleSize, i.size());
        assertTrue(i.contains(primGenerator.getCell(middleCord+1, middleCord)));
        assertTrue(i.contains(primGenerator.getCell(middleCord, middleCord+1)));
        assertTrue(i.contains(primGenerator.getCell(middleCord-1, middleCord)));
        assertTrue(i.contains(primGenerator.getCell(middleCord, middleCord-1)));

        // Somewhere else in the middle
        middleCord = 9;
        int otherMiddleCord = 14;
        ArrayList<Cell> j = primGenerator.getAdjacentCells(primGenerator.getCell(middleCord, otherMiddleCord));
        assertEquals(middleSize, j.size());
        assertTrue(j.contains(primGenerator.getCell(middleCord-1, otherMiddleCord)));
        assertTrue(j.contains(primGenerator.getCell(middleCord, otherMiddleCord-1)));
        assertTrue(j.contains(primGenerator.getCell(middleCord+1, otherMiddleCord)));
        assertTrue(j.contains(primGenerator.getCell(middleCord, otherMiddleCord+1)));
    }

    @Test
    public void testMakeTileUpdateFromCell() {
        // Create a test Cell object
        Cell cell = new Cell(1, 2);
        cell.removeRightBorder();
        cell.removeLeftBorder();
        cell.initializeCell();

        // Test when isCurrent and toHighlight are both false
        boolean isCurrent = false;
        boolean toHighlight = false;
        TileUpdate tileUpdate1 = Generator.makeTileUpdateFromCell(cell, isCurrent, toHighlight);
        TileUpdate expected1 = new TileUpdate(1, 2, true, false, true,
                false, false, true, false,
                false, false);
        assertEquals(expected1, tileUpdate1);

        // Test when isCurrent is true and toHighlight is false
        isCurrent = true;
        toHighlight = false;
        TileUpdate tileUpdate2 = Generator.makeTileUpdateFromCell(cell, isCurrent, toHighlight);
        TileUpdate expected2 = new TileUpdate(1, 2, true, false, true,
                false, false, true, false,
                false, true);
        assertEquals(expected2, tileUpdate2);

        // Test when isCurrent is false and toHighlight is true
        isCurrent = false;
        toHighlight = true;
        TileUpdate tileUpdate3 = Generator.makeTileUpdateFromCell(cell, isCurrent, toHighlight);
        TileUpdate expected3 = new TileUpdate(1, 2, true, false, true,
                false, false, true, false,
                true, false);
        assertEquals(expected3, tileUpdate3);
    }


}
