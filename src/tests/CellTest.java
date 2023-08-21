package tests;

import controller.TileUpdate;
import model.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellTest {

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
        TileUpdate tileUpdate1 = Cell.makeTileUpdateFromCell(cell, isCurrent, toHighlight);
        TileUpdate expected1 = new TileUpdate(1, 2, true, false, true,
                false, false, true, false,
                false, false, false);
        assertEquals(expected1, tileUpdate1);

        // Test when isCurrent is true and toHighlight is false
        isCurrent = true;
        toHighlight = false;
        TileUpdate tileUpdate2 = Cell.makeTileUpdateFromCell(cell, isCurrent, toHighlight);
        TileUpdate expected2 = new TileUpdate(1, 2, true, false, true,
                false, false, true, false,
                false, true, false);
        assertEquals(expected2, tileUpdate2);

        // Test when isCurrent is false and toHighlight is true
        isCurrent = false;
        toHighlight = true;
        TileUpdate tileUpdate3 = Cell.makeTileUpdateFromCell(cell, isCurrent, toHighlight);
        TileUpdate expected3 = new TileUpdate(1, 2, true, false, true,
                false, false, true, false,
                true, false, false);
        assertEquals(expected3, tileUpdate3);
    }
}
