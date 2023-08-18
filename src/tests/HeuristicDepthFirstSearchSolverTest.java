package tests;

import model.Cell;
import model.generators.Generator;
import model.generators.PrimGenerator;
import model.solvers.HeuristicDepthFirstSearchSolver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HeuristicDepthFirstSearchSolverTest {

    @Test
    public void testGetsPastStartStep(){
        PrimGenerator primGenerator = new PrimGenerator();
        primGenerator.finish();
        HeuristicDepthFirstSearchSolver heuristicDepthFirstSearchSolver = new HeuristicDepthFirstSearchSolver(primGenerator.getGrid());
        heuristicDepthFirstSearchSolver.iterate();
        assertTrue(heuristicDepthFirstSearchSolver.isStartStepDone());
    }

    @Test
    public void testNoTeleportingMouse(){
        PrimGenerator primGenerator = new PrimGenerator();
        primGenerator.finish();
        HeuristicDepthFirstSearchSolver heuristicDepthFirstSearchSolver = new HeuristicDepthFirstSearchSolver(primGenerator.getGrid());
        Cell previousCell = heuristicDepthFirstSearchSolver.getCurrentCell();
        Cell currentCell = null;
        while (!heuristicDepthFirstSearchSolver.isDone()){
            heuristicDepthFirstSearchSolver.iterate();
            currentCell = heuristicDepthFirstSearchSolver.getCurrentCell();

            // The mouse should not teleport
            assertTrue(Math.abs(previousCell.getxPos() - currentCell.getxPos()) <= 1);
            assertTrue(Math.abs(previousCell.getyPos() - currentCell.getyPos()) <= 1);
            previousCell = currentCell;
        }
    }

    @Test
    public void testManhattanDistance(){
        Generator generator = new PrimGenerator();
        HeuristicDepthFirstSearchSolver solver = new HeuristicDepthFirstSearchSolver(generator.getGrid());
        Cell a1 = new Cell(0, 0);
        Cell a2 = new Cell(1, 1);
        assertEquals(2, solver.manhattanDistance(a1, a2));

        Cell b1 = new Cell(0, 0);
        Cell b2 = new Cell(5, 5);
        assertEquals(10, solver.manhattanDistance(b1, b2));

        Cell c1 = new Cell(0, 0);
        Cell c2 = new Cell(15, 15);
        assertEquals(30, solver.manhattanDistance(c1, c2));

        Cell d1 = new Cell(4, 2);
        Cell d2 = new Cell(12, 8);
        assertEquals(14, solver.manhattanDistance(d1, d2));

        Cell e1 = new Cell(2, 7);
        Cell e2 = new Cell(3, 8);
        assertEquals(2, solver.manhattanDistance(e1, e2));

        Cell f1 = new Cell(4, 14);
        Cell f2 = new Cell(3, 8);
        assertEquals(7, solver.manhattanDistance(f1, f2));

        Cell g1 = new Cell(12, 7);
        Cell g2 = new Cell(9, 0);
        assertEquals(10, solver.manhattanDistance(g1, g2));
    }

    @Test
    public void testHeuristicImplicitConstructor(){
        PrimGenerator generator = new PrimGenerator();
        HeuristicDepthFirstSearchSolver heuristicDepthFirstSearchSolver = new HeuristicDepthFirstSearchSolver(generator.getGrid());
        int heuristicValue;

        Cell a = new Cell(0, 0);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(a);
        assertEquals(14, heuristicValue);

        Cell b = new Cell(6, 6);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(b);
        assertEquals(2, heuristicValue);

        Cell c = new Cell(7, 6);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(c);
        assertEquals(1, heuristicValue);

        Cell d = new Cell(7, 7);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(d);
        assertEquals(0, heuristicValue);

        Cell e = new Cell(8, 8);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(e);
        assertEquals(0, heuristicValue);

        Cell f = new Cell(9, 8);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(f);
        assertEquals(1, heuristicValue);

        Cell g = new Cell(9, 9);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(g);
        assertEquals(2, heuristicValue);

        Cell h = new Cell(15, 15);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(h);
        assertEquals(14, heuristicValue);

        Cell i = new Cell(15, 0);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(i);
        assertEquals(14, heuristicValue);

        Cell j = new Cell(12, 3);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(j);
        assertEquals(8, heuristicValue);

        Cell k = new Cell(3, 14);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(k);
        assertEquals(10, heuristicValue);

        Cell l = new Cell(0, 15);
        heuristicValue = heuristicDepthFirstSearchSolver.heuristic(l);
        assertEquals(14, heuristicValue);
    }

    @Test
    public void testHeuristicWithExplicitConstructor(){
        PrimGenerator generator = new PrimGenerator();
        Cell startCell = generator.getGrid().getCell(0, 0);
        ArrayList<Cell> endPoints = new ArrayList<>();
        endPoints.add(generator.getGrid().getCell(15, 15));
        endPoints.add(generator.getGrid().getCell(4, 4));
        endPoints.add(generator.getGrid().getCell(2, 14));
        HeuristicDepthFirstSearchSolver heuristicDepthFirstSearchSolver = new HeuristicDepthFirstSearchSolver(generator.getGrid(), startCell, endPoints);

        Cell a = new Cell(4, 1);
        assertEquals(3, heuristicDepthFirstSearchSolver.heuristic(a));

        Cell b = new Cell(15, 0);
        assertEquals(15, heuristicDepthFirstSearchSolver.heuristic(b));

        Cell c = new Cell(6, 4);
        assertEquals(2, heuristicDepthFirstSearchSolver.heuristic(c));

        Cell d = new Cell(2, 6);
        assertEquals(4, heuristicDepthFirstSearchSolver.heuristic(d));

        Cell e = new Cell(1, 9);
        assertEquals(6, heuristicDepthFirstSearchSolver.heuristic(e));

        Cell f = new Cell(8, 9);
        assertEquals(9, heuristicDepthFirstSearchSolver.heuristic(f));

        Cell g = new Cell(15, 9);
        assertEquals(6, heuristicDepthFirstSearchSolver.heuristic(g));

        Cell h = new Cell(5, 10);
        assertEquals(7, heuristicDepthFirstSearchSolver.heuristic(h));

        Cell i = new Cell(13, 12);
        assertEquals(5, heuristicDepthFirstSearchSolver.heuristic(i));

        Cell j = new Cell(1, 13);
        assertEquals(2, heuristicDepthFirstSearchSolver.heuristic(j));

        Cell k = new Cell(8, 14);
        assertEquals(6, heuristicDepthFirstSearchSolver.heuristic(k));

        Cell l = new Cell(9, 14);
        assertEquals(7, heuristicDepthFirstSearchSolver.heuristic(l));

        Cell m = new Cell(8, 15);
        assertEquals(7, heuristicDepthFirstSearchSolver.heuristic(m));

        Cell n = new Cell(9, 15);
        assertEquals(6, heuristicDepthFirstSearchSolver.heuristic(n));

        Cell o = new Cell(2, 15);
        assertEquals(1, heuristicDepthFirstSearchSolver.heuristic(o));

        Cell p = new Cell(12, 15);
        assertEquals(3, heuristicDepthFirstSearchSolver.heuristic(p));
    }

    @Test
    public void testHeuristicComparator(){
        PrimGenerator primGenerator = new PrimGenerator();
        HeuristicDepthFirstSearchSolver heuristicDepthFirstSearchSolver = new HeuristicDepthFirstSearchSolver(primGenerator.getGrid());

        Cell biggerA = new Cell(1, 1);
        Cell smallerA = new Cell(2, 2);
        int resultA = heuristicDepthFirstSearchSolver.getHueristicComparator().compare(smallerA, biggerA);
        assertTrue(resultA < 0);

        Cell biggerB = new Cell(0, 0);
        Cell smallerB = new Cell(6, 6);
        int resultB = heuristicDepthFirstSearchSolver.getHueristicComparator().compare(smallerB, biggerB);
        assertTrue(resultB < 0);

        Cell biggerC = new Cell(4,4);
        Cell smallerC = new Cell(4, 4);
        int resultC = heuristicDepthFirstSearchSolver.getHueristicComparator().compare(smallerC, biggerC);
        assertEquals(0, resultC);
    }

    @Test
    public void testGenerateOrderedStackAppendList(){
        PrimGenerator primGenerator = new PrimGenerator();
        HeuristicDepthFirstSearchSolver heuristicDepthFirstSearchSolver = new HeuristicDepthFirstSearchSolver(primGenerator.getGrid());

        Cell center = heuristicDepthFirstSearchSolver.getGrid().getCell(4, 4);
        Cell right = heuristicDepthFirstSearchSolver.getGrid().getCell(5, 4);
        Cell left = heuristicDepthFirstSearchSolver.getGrid().getCell(3, 4);
        Cell top = heuristicDepthFirstSearchSolver.getGrid().getCell(4, 3);
        Cell bottom = heuristicDepthFirstSearchSolver.getGrid().getCell(4, 5);
        heuristicDepthFirstSearchSolver.getGrid().createPathBetweenCells(center, top);
        heuristicDepthFirstSearchSolver.getGrid().createPathBetweenCells(center, bottom);

        ArrayList<Cell> actualCells = heuristicDepthFirstSearchSolver.generateOrderedStackAppendList(center);
        ArrayList<Cell> expectedCells = new ArrayList<>();
        expectedCells.add(top);
        expectedCells.add(bottom);
        assertEquals(expectedCells, actualCells);

        int bx = 10;
        int by = 10;

        Cell centerB = heuristicDepthFirstSearchSolver.getGrid().getCell(bx, by);
        Cell rightB = heuristicDepthFirstSearchSolver.getGrid().getCell(bx + 1, by);
        Cell leftB = heuristicDepthFirstSearchSolver.getGrid().getCell(bx - 1, by);
        Cell topB = heuristicDepthFirstSearchSolver.getGrid().getCell(bx, by - 1);
        Cell bottomB = heuristicDepthFirstSearchSolver.getGrid().getCell(bx, by + 1);
        heuristicDepthFirstSearchSolver.getGrid().createPathBetweenCells(centerB, rightB);
        heuristicDepthFirstSearchSolver.getGrid().createPathBetweenCells(centerB, leftB);

        ArrayList<Cell> actualCellsB = heuristicDepthFirstSearchSolver.generateOrderedStackAppendList(centerB);
        ArrayList<Cell> expectedCellsB = new ArrayList<>();
        expectedCellsB.add(rightB);
        expectedCellsB.add(leftB);
        assertEquals(expectedCellsB, actualCellsB);

        int cx = 14;
        int cy = 1;

        Cell centerC = heuristicDepthFirstSearchSolver.getGrid().getCell(cx, cy);
        Cell rightC = heuristicDepthFirstSearchSolver.getGrid().getCell(cx + 1, cy);
        Cell leftC = heuristicDepthFirstSearchSolver.getGrid().getCell(cx - 1, cy);
        Cell topC = heuristicDepthFirstSearchSolver.getGrid().getCell(cx, cy - 1);
        Cell bottomC = heuristicDepthFirstSearchSolver.getGrid().getCell(cx, cy + 1);

        ArrayList<Cell> actualCellsC = heuristicDepthFirstSearchSolver.generateOrderedStackAppendList(centerC);
        ArrayList<Cell> expectedCellsC = new ArrayList<>();
        assertEquals(expectedCellsC, actualCellsC);

    }

}
