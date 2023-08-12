package tests;

import model.Cell;
import model.generators.Generator;
import model.generators.PrimGenerator;
import org.junit.jupiter.api.Test;
import utilities.Constants;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratorTest {

    @Test
    public void testGeneratorConstructor(){
        PrimGenerator primGenerator = new PrimGenerator();

        for (int x = Constants.minCellIndex; x < Constants.mazeLength; x++){
            for (int y = Constants.minCellIndex; y < Constants.mazeLength; y++){

                assertTrue(primGenerator.getGrid().getCell(x, y).isBottomBorder());
                assertTrue(primGenerator.getGrid().getCell(x, y).isLeftBorder());
                assertTrue(primGenerator.getGrid().getCell(x, y).isTopBorder());
                assertTrue(primGenerator.getGrid().getCell(x, y).isRightBorder());

            }
        }

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
}
