package tests;

import model.generators.Cell;
import model.generators.PrimGenerator;
import org.junit.jupiter.api.Test;
import utilities.Constants;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PrimGeneratorTest {

    @Test
    public void testAllTestsMultipleTimes(){
        int numberOfTimes = 1000;
        for (int i = 0; i < numberOfTimes; i++){
            testAllCellsInitializedAtEnd();
            testNoCellsBlockedAtEnd();
            testAllCellsReachable();
        }
    }

    @Test
    public void testAllCellsInitializedAtEnd() {
        PrimGenerator primGenerator = new PrimGenerator();
        primGenerator.finish();
        assertTrue(primGenerator.getDoneGenerating());


        for (int x = Constants.minCellIndex; x <= Constants.maxCellIndex; x++) {
            for (int y = Constants.minCellIndex; y <= Constants.maxCellIndex; y++) {
                assertTrue(primGenerator.getCell(x, y).isInitialized());
            }
        }

    }

    @Test
    public void testNoCellsBlockedAtEnd() {
        PrimGenerator primGenerator = new PrimGenerator();
        primGenerator.finish();
        assertTrue(primGenerator.getDoneGenerating());

        for (int x = Constants.minCellIndex; x <= Constants.maxCellIndex; x++) {
            for (int y = Constants.minCellIndex; y <= Constants.maxCellIndex; y++) {
                Cell cell = primGenerator.getCell(x, y);
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

        PrimGenerator primGenerator = new PrimGenerator();
        primGenerator.finish();
        assertTrue(primGenerator.getDoneGenerating());

        Set<Cell> visited = new HashSet<>();
        Queue<Cell> queue = new LinkedList<>();
        Cell start = primGenerator.getCell(Constants.minCellIndex, Constants.minCellIndex);

        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()){
            // Find all reachable neighbors
            Cell current = queue.remove();
            ArrayList<Cell> neighbors = primGenerator.getAdjacentCells(current);
            for (Cell neighbor : neighbors){
                if (cellsMutuallyReachable(current, neighbor) && !(visited.contains(neighbor))){
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
