package tests;

import model.generators.AldousBroderGenerator;
import model.Cell;
import org.junit.jupiter.api.Test;
import utilities.Constants;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AldousBroderGeneratorTest {

    @Test
    public void testAllTestsMultipleTimes() {
        int numberOfTimes = 1000;
        for (int i = 0; i < numberOfTimes; i++) {
            testAllCellsInitializedAtEnd();
            testNoCellsBlockedAtEnd();
            testAllCellsReachable();
        }
    }

    @Test
    public void testAllCellsInitializedAtEnd() {
        AldousBroderGenerator aldousBroderGenerator = new AldousBroderGenerator();
        aldousBroderGenerator.finish();
        assertTrue(aldousBroderGenerator.getDoneGenerating());


        for (int x = Constants.minCellIndex; x <= Constants.maxCellIndex; x++) {
            for (int y = Constants.minCellIndex; y <= Constants.maxCellIndex; y++) {
                assertTrue(aldousBroderGenerator.getGrid().getCell(x, y).isInitialized());
            }
        }

    }

    @Test
    public void testNoCellsBlockedAtEnd() {
        AldousBroderGenerator aldousBroderGenerator = new AldousBroderGenerator();
        aldousBroderGenerator.finish();
        assertTrue(aldousBroderGenerator.getDoneGenerating());

        for (int x = Constants.minCellIndex; x <= Constants.maxCellIndex; x++) {
            for (int y = Constants.minCellIndex; y <= Constants.maxCellIndex; y++) {
                Cell cell = aldousBroderGenerator.getGrid().getCell(x, y);
                boolean isBlockedOff = cell.isBottomBorder() && cell.isTopBorder() && cell.isRightBorder() && cell.isLeftBorder();
                assertFalse(isBlockedOff);
            }
        }
    }

    @Test
    public void testAllCellsReachable() {
        // If all cells are reachable from a chosen start cell that means every cell is connected to every
        // other cell by at least one cell, meaning that the maze is solvable from any start and end points.
        // Use simple BFS to try to find every cell.

        AldousBroderGenerator aldousBroderGenerator = new AldousBroderGenerator();
        aldousBroderGenerator.finish();
        assertTrue(aldousBroderGenerator.getDoneGenerating());

        Set<Cell> visited = new HashSet<>();
        Queue<Cell> queue = new LinkedList<>();
        Cell start = aldousBroderGenerator.getGrid().getCell(Constants.minCellIndex, Constants.minCellIndex);

        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            // Find all reachable neighbors
            Cell current = queue.remove();
            ArrayList<Cell> neighbors = aldousBroderGenerator.getGrid().getAdjacentCells(current);
            for (Cell neighbor : neighbors) {
                if (cellsMutuallyReachable(current, neighbor) && !(visited.contains(neighbor))) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        assertEquals(Constants.mazeLength * Constants.mazeLength, visited.size());

    }

    private boolean cellsMutuallyReachable(Cell from, Cell to) {

        int fromX = from.getxPos();
        int fromY = from.getyPos();
        int toX = to.getxPos();
        int toY = to.getyPos();

        if ((fromY == toY) && (fromX > toX)) {
            return !from.isLeftBorder() && !to.isRightBorder();
        } else if ((fromY == toY) && (fromX < toX)) {
            return !from.isRightBorder() && !to.isLeftBorder();
        } else if ((fromX == toX) && (fromY > toY)) {
            return !from.isTopBorder() && !to.isBottomBorder();
        } else if ((fromX == toX) && (fromY < toY)) {
            return !from.isBottomBorder() && !to.isTopBorder();
        } else {
            throw new IllegalStateException("Cells provided have malformed coordinates.");

        }
    }
}
