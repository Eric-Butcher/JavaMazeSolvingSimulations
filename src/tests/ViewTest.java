package tests;

import controller.TileUpdate;
import controller.ViewUpdatePacket;
import org.junit.jupiter.api.Test;
import utilities.Constants;
import view.Tile;
import view.View;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ViewTest {

    @Test
    public void testDefaultViewState() {
        View view = new View();

        for (int y = Constants.minCellIndex; y <= Constants.maxCellIndex; y++) {
            for (int x = Constants.minCellIndex; x <= Constants.maxCellIndex; x++) {
                Tile tile = view.getMazePanel().getTile(x, y);
                assertEquals(x, tile.getxPos());
                assertEquals(y, tile.getyPos());
                assertTrue(tile.isHasTopBorder());
                assertTrue(tile.isHasRightBorder());
                assertTrue(tile.isHasBottomBorder());
                assertTrue(tile.isHasLeftBorder());
                assertFalse(tile.isHasMouse());
                assertFalse(tile.isTraversed());
                assertFalse(tile.isInitialized());
                assertFalse(tile.isHighlighted());
            }
        }
    }

    @Test
    public void testUpdateView_singleChange() {
        View view = new View();

        TileUpdate tileUpdateA = new TileUpdate(3, 11, false, true, true, false, false, true, false, false, false, false);
        ViewUpdatePacket packetA = new ViewUpdatePacket(new ArrayList<TileUpdate>(300));
        packetA.addTileUpdate(tileUpdateA);
        view.updateView(packetA);

        Tile tile = view.getMazePanel().getTile(3, 11);
        assertEquals(3, tile.getxPos());
        assertEquals(11, tile.getyPos());
        assertFalse(tile.isHasTopBorder());
        assertTrue(tile.isHasRightBorder());
        assertTrue(tile.isHasBottomBorder());
        assertFalse(tile.isHasLeftBorder());
        assertFalse(tile.isHasMouse());
        assertFalse(tile.isTraversed());
        assertTrue(tile.isInitialized());
        assertFalse(tile.isHighlighted());


        TileUpdate tileUpdateB = new TileUpdate(3, 11, false, false, true, false, true, true, true, false, false, false);
        ViewUpdatePacket packetB = new ViewUpdatePacket(new ArrayList<TileUpdate>(300));
        packetB.addTileUpdate(tileUpdateB);
        view.updateView(packetB);
        assertEquals(3, tile.getxPos());
        assertEquals(11, tile.getyPos());
        assertFalse(tile.isHasTopBorder());
        assertFalse(tile.isHasRightBorder());
        assertTrue(tile.isHasBottomBorder());
        assertFalse(tile.isHasLeftBorder());
        assertTrue(tile.isHasMouse());
        assertTrue(tile.isTraversed());
        assertTrue(tile.isInitialized());
        assertFalse(tile.isHighlighted());

    }

    @Test
    public void testUpdateView_multipleLines() {
        View view = new View();

        ViewUpdatePacket packetA = new ViewUpdatePacket(new ArrayList<TileUpdate>(300));
        for (int i = 0; i < 10; i++) {
            TileUpdate tileUpdate = new TileUpdate(i, 2, false, false, true, false, false, true, false, false, false, false);
            packetA.addTileUpdate(tileUpdate);
        }
        view.updateView(packetA);
        for (int i = 0; i < 10; i++) {
            Tile tile = view.getMazePanel().getTile(i, 2);
            assertEquals(i, tile.getxPos());
            assertEquals(2, tile.getyPos());
            assertFalse(tile.isHasTopBorder());
            assertFalse(tile.isHasRightBorder());
            assertTrue(tile.isHasBottomBorder());
            assertFalse(tile.isHasLeftBorder());
            assertFalse(tile.isHasMouse());
            assertFalse(tile.isTraversed());
            assertTrue(tile.isInitialized());
            assertFalse(tile.isHighlighted());
        }


        ViewUpdatePacket packetB = new ViewUpdatePacket(new ArrayList<TileUpdate>(300));
        for (int i = Constants.minCellIndex; i <= Constants.maxCellIndex; i++) {
            TileUpdate tileUpdate = new TileUpdate(12, i, true, true, false, false, false, false, true, true, false, false);
            packetB.addTileUpdate(tileUpdate);
        }
        view.updateView(packetB);
        for (int i = Constants.minCellIndex; i <= Constants.maxCellIndex; i++) {
            Tile tile = view.getMazePanel().getTile(12, i);
            assertEquals(12, tile.getxPos());
            assertEquals(i, tile.getyPos());
            assertTrue(tile.isHasTopBorder());
            assertTrue(tile.isHasRightBorder());
            assertFalse(tile.isHasBottomBorder());
            assertFalse(tile.isHasLeftBorder());
            assertFalse(tile.isHasMouse());
            assertTrue(tile.isTraversed());
            assertFalse(tile.isInitialized());
            assertTrue(tile.isHighlighted());
        }


        ViewUpdatePacket packetC = new ViewUpdatePacket(new ArrayList<TileUpdate>(300));
        for (int i = Constants.minCellIndex; i <= Constants.maxCellIndex; i++) {
            TileUpdate tileUpdate = new TileUpdate(12, i, false, true, false, false, true, false, true, false, false, false);
            packetC.addTileUpdate(tileUpdate);
        }
        view.updateView(packetC);
        for (int i = Constants.minCellIndex; i <= Constants.maxCellIndex; i++) {
            Tile tile = view.getMazePanel().getTile(12, i);
            assertEquals(12, tile.getxPos());
            assertEquals(i, tile.getyPos());
            assertFalse(tile.isHasTopBorder());
            assertTrue(tile.isHasRightBorder());
            assertFalse(tile.isHasBottomBorder());
            assertFalse(tile.isHasLeftBorder());
            assertTrue(tile.isHasMouse());
            assertTrue(tile.isTraversed());
            assertFalse(tile.isInitialized());
            assertFalse(tile.isHighlighted());
        }
    }


}
