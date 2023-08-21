package tests;

import model.Cell;
import model.Grid;
import model.generators.Generator;
import model.generators.PrimGenerator;
import model.solvers.GreedyBestFirstSearchSolver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GreedyBestFirstSearchSolverTest {

    @Test
    public void testGetsPastStartStep() {
        PrimGenerator primGenerator = new PrimGenerator();
        primGenerator.finish();
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolver = new GreedyBestFirstSearchSolver(primGenerator.getGrid());
        greedyBestFirstSearchSolver.iterate();
        assertTrue(greedyBestFirstSearchSolver.isStartStepDone());
    }

    @Test
    public void testNoTeleportingMouse() {
        PrimGenerator primGenerator = new PrimGenerator();
        primGenerator.finish();
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolver = new GreedyBestFirstSearchSolver(primGenerator.getGrid());
        Cell previousCell = greedyBestFirstSearchSolver.getCurrentCell();
        Cell currentCell = null;
        while (!greedyBestFirstSearchSolver.isDone()) {
            greedyBestFirstSearchSolver.iterate();
            currentCell = greedyBestFirstSearchSolver.getCurrentCell();

            // The mouse should not teleport
            assertTrue(Math.abs(previousCell.getxPos() - currentCell.getxPos()) <= 1);
            assertTrue(Math.abs(previousCell.getyPos() - currentCell.getyPos()) <= 1);
            previousCell = currentCell;
        }
    }

    @Test
    public void testGetsToDestination() {
        Generator generator = new PrimGenerator();
        generator.finish();
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolver = new GreedyBestFirstSearchSolver(generator.getGrid());
        greedyBestFirstSearchSolver.finish();
        assertTrue(greedyBestFirstSearchSolver.isDone());
        assertTrue(greedyBestFirstSearchSolver.getEndPoints().contains(greedyBestFirstSearchSolver.getCurrentCell()));
    }

    @Test
    public void testManhattanDistance() {
        Generator generator = new PrimGenerator();
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolver = new GreedyBestFirstSearchSolver(generator.getGrid());
        Cell a1 = new Cell(0, 0);
        Cell a2 = new Cell(1, 1);
        assertEquals(2, greedyBestFirstSearchSolver.manhattanDistance(a1, a2));

        Cell b1 = new Cell(0, 0);
        Cell b2 = new Cell(5, 5);
        assertEquals(10, greedyBestFirstSearchSolver.manhattanDistance(b1, b2));

        Cell c1 = new Cell(0, 0);
        Cell c2 = new Cell(15, 15);
        assertEquals(30, greedyBestFirstSearchSolver.manhattanDistance(c1, c2));

        Cell d1 = new Cell(4, 2);
        Cell d2 = new Cell(12, 8);
        assertEquals(14, greedyBestFirstSearchSolver.manhattanDistance(d1, d2));

        Cell e1 = new Cell(2, 7);
        Cell e2 = new Cell(3, 8);
        assertEquals(2, greedyBestFirstSearchSolver.manhattanDistance(e1, e2));

        Cell f1 = new Cell(4, 14);
        Cell f2 = new Cell(3, 8);
        assertEquals(7, greedyBestFirstSearchSolver.manhattanDistance(f1, f2));

        Cell g1 = new Cell(12, 7);
        Cell g2 = new Cell(9, 0);
        assertEquals(10, greedyBestFirstSearchSolver.manhattanDistance(g1, g2));
    }

    @Test
    public void testHeuristicImplicitConstructor() {
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
    public void testHeuristicWithExplicitConstructor() {
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
    public void testHeuristicComparator() {
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

        Cell biggerC = new Cell(4, 4);
        Cell smallerC = new Cell(4, 4);
        int resultC = greedyBestFirstSearchSolver.getHueristicComparator().compare(smallerC, biggerC);
        assertEquals(0, resultC);
    }

    @Test
    public void testGenerateBacktrace() {
        /*
         Path is just one cell
         XXXX
         XmeX
         XsXX
         XXXX
        */
        Grid gridA = new Grid();
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolverA = new GreedyBestFirstSearchSolver(gridA);
        Cell startCellA = gridA.getCell(4, 4);
        Cell middleCellA = gridA.getCell(4, 3);
        Cell endCellA = gridA.getCell(5, 3);
        startCellA.setTraversed(true);
        middleCellA.setTraversed(true);
        endCellA.setTraversed(true);
        gridA.createPathBetweenCells(startCellA, middleCellA);
        gridA.createPathBetweenCells(middleCellA, endCellA);
        Stack<Cell> backtraceA = greedyBestFirstSearchSolverA.generateBacktrace(startCellA, endCellA);
        assertEquals(1, backtraceA.size());
        assertEquals(middleCellA, backtraceA.pop());

        /*
         Path is two cells
         XXXX
         XmeX
         XmXX
         XsXX
         XXXX
        */
        Grid gridB = new Grid();
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolverB = new GreedyBestFirstSearchSolver(gridB);
        Cell startCellB = gridB.getCell(7, 7);
        Cell lowerMiddleCellB = gridB.getCell(7, 6);
        Cell upperMiddleCellB = gridB.getCell(7, 5);
        Cell endCellB = gridB.getCell(8, 5);
        startCellB.setTraversed(true);
        lowerMiddleCellB.setTraversed(true);
        upperMiddleCellB.setTraversed(true);
        endCellB.setTraversed(true);
        gridB.createPathBetweenCells(startCellB, lowerMiddleCellB);
        gridB.createPathBetweenCells(lowerMiddleCellB, upperMiddleCellB);
        gridB.createPathBetweenCells(upperMiddleCellB, endCellB);
        Stack<Cell> backtraceB = greedyBestFirstSearchSolverB.generateBacktrace(startCellB, endCellB);
        Stack<Cell> expectedBacktraceB = new Stack<>();
        expectedBacktraceB.add(upperMiddleCellB);
        expectedBacktraceB.add(lowerMiddleCellB);
        assertEquals(2, backtraceB.size());
        assertEquals(expectedBacktraceB, backtraceB);

    }

    @Test
    public void testGenerateBacktraceComplicated() {
        /*
         * XXXllXXX
         * XXXXlXXX
         * bXXXllll
         * bbbXlXXl
         * XXbXagll
         * XXbXaXXX
         * bbbXaXXX
         * XXbbsXXX
         * XXXXdXXX
         * XXdddddX
         * XXXXXXdX
         */

        Grid gridC = new Grid();
        GreedyBestFirstSearchSolver greedyBestFirstSearchSolverC = new GreedyBestFirstSearchSolver(gridC);
        // l, for longPath
        Cell longPath_1 = gridC.getCell(5, 5);
        Cell longPath_2 = gridC.getCell(6, 5);
        Cell longPath_3 = gridC.getCell(6, 6);
        Cell longPath_4 = gridC.getCell(6, 7);
        Cell longPath_5 = gridC.getCell(6, 8);
        Cell longPath_6 = gridC.getCell(7, 7);
        Cell longPath_7 = gridC.getCell(8, 7);
        Cell longPath_8 = gridC.getCell(9, 7);
        Cell longPath_9 = gridC.getCell(9, 8);
        Cell longPath_10 = gridC.getCell(9, 9);
        Cell longPath_11 = gridC.getCell(8, 9);
        longPath_1.setTraversed(true);
        longPath_2.setTraversed(true);
        longPath_3.setTraversed(true);
        longPath_4.setTraversed(true);
        longPath_5.setTraversed(true);
        longPath_6.setTraversed(true);
        longPath_7.setTraversed(true);
        longPath_8.setTraversed(true);
        longPath_9.setTraversed(true);
        longPath_10.setTraversed(true);
        longPath_11.setTraversed(true);
        gridC.createPathBetweenCells(longPath_1, longPath_2);
        gridC.createPathBetweenCells(longPath_2, longPath_3);
        gridC.createPathBetweenCells(longPath_3, longPath_4);
        gridC.createPathBetweenCells(longPath_4, longPath_5);
        gridC.createPathBetweenCells(longPath_4, longPath_6);
        gridC.createPathBetweenCells(longPath_6, longPath_7);
        gridC.createPathBetweenCells(longPath_7, longPath_8);
        gridC.createPathBetweenCells(longPath_8, longPath_9);
        gridC.createPathBetweenCells(longPath_9, longPath_10);
        gridC.createPathBetweenCells(longPath_10, longPath_11);

        // a, for answer path
        Cell answerPath_1 = gridC.getCell(6, 9);
        Cell answerPath_2 = gridC.getCell(6, 10);
        Cell answerPath_3 = gridC.getCell(6, 11);
        answerPath_1.setTraversed(true);
        answerPath_2.setTraversed(true);
        answerPath_3.setTraversed(true);
        gridC.createPathBetweenCells(answerPath_1, answerPath_2);
        gridC.createPathBetweenCells(answerPath_2, answerPath_3);

        // b, for badPath
        Cell badPath_1 = gridC.getCell(5, 12);
        Cell badPath_2 = gridC.getCell(4, 12);
        Cell badPath_3 = gridC.getCell(4, 11);
        Cell badPath_4 = gridC.getCell(3, 11);
        Cell badPath_5 = gridC.getCell(2, 11);
        Cell badPath_6 = gridC.getCell(4, 10);
        Cell badPath_7 = gridC.getCell(4, 9);
        Cell badPath_8 = gridC.getCell(4, 8);
        Cell badPath_9 = gridC.getCell(3, 8);
        Cell badPath_10 = gridC.getCell(2, 8);
        Cell badPath_11 = gridC.getCell(2, 7);
        badPath_1.setTraversed(true);
        badPath_2.setTraversed(true);
        badPath_3.setTraversed(true);
        badPath_4.setTraversed(true);
        badPath_5.setTraversed(true);
        badPath_6.setTraversed(true);
        badPath_7.setTraversed(true);
        badPath_8.setTraversed(true);
        badPath_9.setTraversed(true);
        badPath_10.setTraversed(true);
        badPath_11.setTraversed(true);
        gridC.createPathBetweenCells(badPath_1, badPath_2);
        gridC.createPathBetweenCells(badPath_2, badPath_3);
        gridC.createPathBetweenCells(badPath_3, badPath_4);
        gridC.createPathBetweenCells(badPath_4, badPath_5);
        gridC.createPathBetweenCells(badPath_3, badPath_6);
        gridC.createPathBetweenCells(badPath_6, badPath_7);
        gridC.createPathBetweenCells(badPath_7, badPath_8);
        gridC.createPathBetweenCells(badPath_8, badPath_9);
        gridC.createPathBetweenCells(badPath_9, badPath_10);
        gridC.createPathBetweenCells(badPath_10, badPath_11);

        // d, for dumbPath
        Cell dumbPath_1 = gridC.getCell(6, 13);
        Cell dumbPath_2 = gridC.getCell(6, 14);
        Cell dumbPath_3 = gridC.getCell(5, 14);
        Cell dumbPath_4 = gridC.getCell(4, 14);
        Cell dumbPath_5 = gridC.getCell(7, 14);
        Cell dumbPath_6 = gridC.getCell(8, 14);
        Cell dumbPath_7 = gridC.getCell(8, 15);
        dumbPath_1.setTraversed(true);
        dumbPath_2.setTraversed(true);
        dumbPath_3.setTraversed(true);
        dumbPath_4.setTraversed(true);
        dumbPath_5.setTraversed(true);
        dumbPath_6.setTraversed(true);
        dumbPath_7.setTraversed(true);
        gridC.createPathBetweenCells(dumbPath_1, dumbPath_2);
        gridC.createPathBetweenCells(dumbPath_2, dumbPath_3);
        gridC.createPathBetweenCells(dumbPath_3, dumbPath_4);
        gridC.createPathBetweenCells(dumbPath_2, dumbPath_5);
        gridC.createPathBetweenCells(dumbPath_5, dumbPath_6);
        gridC.createPathBetweenCells(dumbPath_6, dumbPath_7);

        // s, for startCell
        Cell startCellC = gridC.getCell(6, 12);
        startCellC.setTraversed(true);
        gridC.createPathBetweenCells(startCellC, dumbPath_1);
        gridC.createPathBetweenCells(startCellC, badPath_1);
        gridC.createPathBetweenCells(startCellC, answerPath_3);

        // g, for goalCell
        Cell goalCellC = gridC.getCell(7, 9);
        goalCellC.setTraversed(true);
        gridC.createPathBetweenCells(goalCellC, longPath_11);
        gridC.createPathBetweenCells(goalCellC, answerPath_1);

        Stack<Cell> backtraceC = greedyBestFirstSearchSolverC.generateBacktrace(startCellC, goalCellC);
        assertEquals(3, backtraceC.size());
        Stack<Cell> expectedBacktraceC = new Stack<>();
        expectedBacktraceC.add(answerPath_1);
        expectedBacktraceC.add(answerPath_2);
        expectedBacktraceC.add(answerPath_3);
        assertEquals(expectedBacktraceC, backtraceC);
    }

    //TODO: Add more tests for backtracking
}
