package tests;

import model.Cell;
import model.generators.Generator;
import model.generators.PrimGenerator;
import model.solvers.GreedyBestFirstSearchSolver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GreedyBestFirstSearchSolverTest {

    @Test
    public void testManhattanDistance(){
        Generator generator = new PrimGenerator();
        GreedyBestFirstSearchSolver solver = new GreedyBestFirstSearchSolver(generator.getGrid());
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
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolver = new GreedyBestFirstSearchSolver(generator.getGrid());
        int heuristicValue;

        Cell a = new Cell(0, 0);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(a);
        assertEquals(14, heuristicValue);

        Cell b = new Cell(6, 6);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(b);
        assertEquals(2, heuristicValue);

        Cell c = new Cell(7, 6);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(c);
        assertEquals(1, heuristicValue);

        Cell d = new Cell(7, 7);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(d);
        assertEquals(0, heuristicValue);

        Cell e = new Cell(8, 8);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(e);
        assertEquals(0, heuristicValue);

        Cell f = new Cell(9, 8);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(f);
        assertEquals(1, heuristicValue);

        Cell g = new Cell(9, 9);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(g);
        assertEquals(2, heuristicValue);

        Cell h = new Cell(15, 15);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(h);
        assertEquals(14, heuristicValue);

        Cell i = new Cell(15, 0);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(i);
        assertEquals(14, heuristicValue);

        Cell j = new Cell(12, 3);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(j);
        assertEquals(8, heuristicValue);

        Cell k = new Cell(3, 14);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(k);
        assertEquals(10, heuristicValue);

        Cell l = new Cell(0, 15);
        heuristicValue = greedyBestFirstSearchSolver.heuristic(l);
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
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolver = new GreedyBestFirstSearchSolver(generator.getGrid(), startCell, endPoints);

        Cell a = new Cell(4, 1);
        assertEquals(3, greedyBestFirstSearchSolver.heuristic(a));

        Cell b = new Cell(15, 0);
        assertEquals(15, greedyBestFirstSearchSolver.heuristic(b));

        Cell c = new Cell(6, 4);
        assertEquals(2, greedyBestFirstSearchSolver.heuristic(c));

        Cell d = new Cell(2, 6);
        assertEquals(4, greedyBestFirstSearchSolver.heuristic(d));

        Cell e = new Cell(1, 9);
        assertEquals(6, greedyBestFirstSearchSolver.heuristic(e));

        Cell f = new Cell(8, 9);
        assertEquals(9, greedyBestFirstSearchSolver.heuristic(f));

        Cell g = new Cell(15, 9);
        assertEquals(6, greedyBestFirstSearchSolver.heuristic(g));

        Cell h = new Cell(5, 10);
        assertEquals(7, greedyBestFirstSearchSolver.heuristic(h));

        Cell i = new Cell(13, 12);
        assertEquals(5, greedyBestFirstSearchSolver.heuristic(i));

        Cell j = new Cell(1, 13);
        assertEquals(2, greedyBestFirstSearchSolver.heuristic(j));

        Cell k = new Cell(8, 14);
        assertEquals(6, greedyBestFirstSearchSolver.heuristic(k));

        Cell l = new Cell(9, 14);
        assertEquals(7, greedyBestFirstSearchSolver.heuristic(l));

        Cell m = new Cell(8, 15);
        assertEquals(7, greedyBestFirstSearchSolver.heuristic(m));

        Cell n = new Cell(9, 15);
        assertEquals(6, greedyBestFirstSearchSolver.heuristic(n));

        Cell o = new Cell(2, 15);
        assertEquals(1, greedyBestFirstSearchSolver.heuristic(o));

        Cell p = new Cell(12, 15);
        assertEquals(3, greedyBestFirstSearchSolver.heuristic(p));
    }

    @Test
    public void testHeuristicComparator(){
        PrimGenerator primGenerator = new PrimGenerator();
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolver = new GreedyBestFirstSearchSolver(primGenerator.getGrid());

        Cell biggerA = new Cell(1, 1);
        Cell smallerA = new Cell(2, 2);
        int resultA = greedyBestFirstSearchSolver.getHueristicComparator().compare(smallerA, biggerA);
        assertTrue(resultA < 0);

        Cell biggerB = new Cell(0, 0);
        Cell smallerB = new Cell(6, 6);
        int resultB = greedyBestFirstSearchSolver.getHueristicComparator().compare(smallerB, biggerB);
        assertTrue(resultB < 0);

        Cell biggerC = new Cell(4,4);
        Cell smallerC = new Cell(4, 4);
        int resultC = greedyBestFirstSearchSolver.getHueristicComparator().compare(smallerC, biggerC);
        assertEquals(0, resultC);
    }

    @Test
    public void testGenerateOrderedStackAppendList(){
        PrimGenerator primGenerator = new PrimGenerator();
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolver = new GreedyBestFirstSearchSolver(primGenerator.getGrid());

        Cell center = greedyBestFirstSearchSolver.getGrid().getCell(4, 4);
        Cell right = greedyBestFirstSearchSolver.getGrid().getCell(5, 4);
        Cell left = greedyBestFirstSearchSolver.getGrid().getCell(3, 4);
        Cell top = greedyBestFirstSearchSolver.getGrid().getCell(4, 3);
        Cell bottom = greedyBestFirstSearchSolver.getGrid().getCell(4, 5);
        greedyBestFirstSearchSolver.getGrid().createPathBetweenCells(center, top);
        greedyBestFirstSearchSolver.getGrid().createPathBetweenCells(center, bottom);

        ArrayList<Cell> actualCells = greedyBestFirstSearchSolver.generateOrderedStackAppendList(center);
        ArrayList<Cell> expectedCells = new ArrayList<>();
        expectedCells.add(top);
        expectedCells.add(bottom);
        assertEquals(expectedCells, actualCells);

        int bx = 10;
        int by = 10;

        Cell centerB = greedyBestFirstSearchSolver.getGrid().getCell(bx, by);
        Cell rightB = greedyBestFirstSearchSolver.getGrid().getCell(bx + 1, by);
        Cell leftB = greedyBestFirstSearchSolver.getGrid().getCell(bx - 1, by);
        Cell topB = greedyBestFirstSearchSolver.getGrid().getCell(bx, by - 1);
        Cell bottomB = greedyBestFirstSearchSolver.getGrid().getCell(bx, by + 1);
        greedyBestFirstSearchSolver.getGrid().createPathBetweenCells(centerB, rightB);
        greedyBestFirstSearchSolver.getGrid().createPathBetweenCells(centerB, leftB);

        ArrayList<Cell> actualCellsB = greedyBestFirstSearchSolver.generateOrderedStackAppendList(centerB);
        ArrayList<Cell> expectedCellsB = new ArrayList<>();
        expectedCellsB.add(rightB);
        expectedCellsB.add(leftB);
        assertEquals(expectedCellsB, actualCellsB);

        int cx = 14;
        int cy = 1;

        Cell centerC = greedyBestFirstSearchSolver.getGrid().getCell(cx, cy);
        Cell rightC = greedyBestFirstSearchSolver.getGrid().getCell(cx + 1, cy);
        Cell leftC = greedyBestFirstSearchSolver.getGrid().getCell(cx - 1, cy);
        Cell topC = greedyBestFirstSearchSolver.getGrid().getCell(cx, cy - 1);
        Cell bottomC = greedyBestFirstSearchSolver.getGrid().getCell(cx, cy + 1);

        ArrayList<Cell> actualCellsC = greedyBestFirstSearchSolver.generateOrderedStackAppendList(centerC);
        ArrayList<Cell> expectedCellsC = new ArrayList<>();
        assertEquals(expectedCellsC, actualCellsC);

    }

}
