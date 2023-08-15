package tests;

import model.Cell;
import model.generators.Generator;
import model.generators.PrimGenerator;
import model.solvers.GreedyBestFirstSearchSolver;
import model.solvers.Solver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTest {

    @Test
    public void testImplicitConstructor(){
        Generator generator = new PrimGenerator();
        Solver solver =  new GreedyBestFirstSearchSolver(generator.getGrid());

        ArrayList<Cell> actualEndpoints = solver.getEndPoints();
        Cell actualStartPoint = solver.getStartPoint();

        ArrayList<Cell> expectedEndpoints = new ArrayList<>();
        expectedEndpoints.add(new Cell(7, 7));
        expectedEndpoints.add(new Cell(8, 7));
        expectedEndpoints.add(new Cell(7, 8));
        expectedEndpoints.add(new Cell(8, 8));

        Cell expectedStartPoint = new Cell(0, 0);

        assertEquals(expectedStartPoint, actualStartPoint);
        assertEquals(expectedEndpoints, actualEndpoints);
    }

    @Test
    public void testExplicitConstructor(){
        Generator generator = new PrimGenerator();

        ArrayList<Cell> endpoints = new ArrayList<>();
        endpoints.add(new Cell(1, 2));
        endpoints.add(new Cell(3, 4));
        endpoints.add(new Cell(6, 6));
        endpoints.add(new Cell(15, 15));

        Cell startPoint = new Cell(5, 10);

        Solver solver =  new GreedyBestFirstSearchSolver(generator.getGrid(), startPoint, endpoints);

        assertEquals(startPoint, solver.getStartPoint());
        assertEquals(endpoints, solver.getEndPoints());

    }

    @Test
    public void testGetUnTraversedCells(){
        ArrayList<Cell> input = new ArrayList<>();

        // Create cells
        Cell cellA = new Cell(1, 1);
        cellA.setTraversed(true);
        input.add(cellA);

        Cell cellB = new Cell(5, 15);
        cellB.setTraversed(false);
        input.add(cellB);

        Cell cellC = new Cell(3, 4);
        cellC.setTraversed(false);
        input.add(cellC);

        Cell cellD = new Cell(0, 12);
        cellD.setTraversed(true);
        input.add(cellD);

        Cell cellE = new Cell(2, 2);
        cellE.setTraversed(true);
        input.add(cellE);

        ArrayList<Cell> expected = new ArrayList<>();
        expected.add(cellB);
        expected.add(cellC);

        assertEquals(expected, Solver.getUnTraversedCells(input));
    }



    @Test
    public void testGetUnTraversedReachableNeighbors(){
        PrimGenerator primGenerator = new PrimGenerator();
        Solver solver = new GreedyBestFirstSearchSolver(primGenerator.getGrid());

        int ax = 5;
        int ay = 5;
        Cell centerA = solver.getGrid().getCell(ax, ay);
        Cell topA = solver.getGrid().getCell(ax, ay-1);
        Cell rightA = solver.getGrid().getCell(ax+1, ay);
        Cell bottomA = solver.getGrid().getCell(ax, ay+1);
        Cell leftA = solver.getGrid().getCell(ax-1, ay);
        solver.getGrid().createPathBetweenCells(centerA, topA);
        solver.getGrid().createPathBetweenCells(centerA, bottomA);
        solver.getGrid().createPathBetweenCells(centerA, leftA);
        solver.getGrid().createPathBetweenCells(centerA, rightA);
        assertEquals(4, solver.getUntraversedReachableNeighbors(centerA).size());
        ArrayList<Cell> expected = new ArrayList<>();
        expected.add(topA);
        expected.add(rightA);
        expected.add(bottomA);
        expected.add(leftA);
        ArrayList<Cell> actual = solver.getUntraversedReachableNeighbors(centerA);
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));


        int bx = 7;
        int by = 10;
        Cell centerB = solver.getGrid().getCell(bx, by);
        Cell topB = solver.getGrid().getCell(bx, by-1);
        Cell rightB = solver.getGrid().getCell(bx+1, by);
        Cell bottomB = solver.getGrid().getCell(bx, by+1);
        Cell leftB = solver.getGrid().getCell(bx-1, by);
        solver.getGrid().createPathBetweenCells(centerB, topB);
        solver.getGrid().createPathBetweenCells(centerB, bottomB);
        ArrayList<Cell> expected2 = new ArrayList<>();
        expected2.add(topB);
        expected2.add(bottomB);
        ArrayList<Cell> actual2 = solver.getUntraversedReachableNeighbors(centerB);
        assertEquals(2, actual2.size());
        assertTrue(expected2.containsAll(actual2) && actual2.containsAll(expected2));

        int cx = 3;
        int cy = 2;
        Cell centerC = solver.getGrid().getCell(cx, cy);
        Cell topC = solver.getGrid().getCell(cx, cy-1);
        Cell rightC = solver.getGrid().getCell(cx+1, cy);
        Cell bottomC = solver.getGrid().getCell(cx, cy+1);
        Cell leftC = solver.getGrid().getCell(cx-1, cy);
        solver.getGrid().createPathBetweenCells(centerC, rightC);
        solver.getGrid().createPathBetweenCells(centerC, leftC);
        ArrayList<Cell> expected3 = new ArrayList<>();
        expected3.add(rightC);
        expected3.add(leftC);
        ArrayList<Cell> actual3 = solver.getUntraversedReachableNeighbors(centerC);
        assertEquals(2, actual3.size());
        assertTrue(expected3.containsAll(actual3) && actual3.containsAll(expected3));

        int dx = 14;
        int dy = 14;
        Cell centerD = solver.getGrid().getCell(dx, dy);
        Cell topD = solver.getGrid().getCell(dx, dy-1);
        Cell rightD = solver.getGrid().getCell(dx+1, dy);
        Cell bottomD = solver.getGrid().getCell(dx, dy+1);
        Cell leftD = solver.getGrid().getCell(dx-1, dy);
        solver.getGrid().createPathBetweenCells(centerD, topD);
        ArrayList<Cell> expected4 = new ArrayList<>();
        expected4.add(topD);
        ArrayList<Cell> actual4 = solver.getUntraversedReachableNeighbors(centerD);
        assertEquals(1, actual4.size());
        assertTrue(expected4.containsAll(actual4) && actual4.containsAll(expected4));

        int ex = 9;
        int ey = 1;
        Cell centerE = solver.getGrid().getCell(ex, ey);
        Cell topE = solver.getGrid().getCell(ex, ey-1);
        Cell rightE = solver.getGrid().getCell(ex+1, ey);
        Cell bottomE = solver.getGrid().getCell(ex, ey+1);
        Cell leftE = solver.getGrid().getCell(ex-1, ey);
        ArrayList<Cell> expected5 = new ArrayList<>();
        ArrayList<Cell> actual5 = solver.getUntraversedReachableNeighbors(centerE);
        assertEquals(0, actual5.size());
        assertTrue(expected5.containsAll(actual5) && actual5.containsAll(expected5));
    }

    @Test
    public void testAtDestination(){
        Generator generator = new PrimGenerator();
        Solver solver =  new GreedyBestFirstSearchSolver(generator.getGrid());

        ArrayList<Cell> destinations = new ArrayList<>();
        destinations.add(new Cell(7, 7));
        destinations.add(new Cell(7, 8));
        destinations.add(new Cell(8, 7));
        destinations.add(new Cell(8, 8));
        for (Cell destination : destinations){
            assertTrue(solver.atDestination(destination));
        }

        ArrayList<Cell> notDestinations = new ArrayList<>();
        notDestinations.add(new Cell(5, 7));
        notDestinations.add(new Cell(3, 15));
        notDestinations.add(new Cell(0, 0));
        notDestinations.add(new Cell(15, 15));
        notDestinations.add(new Cell(1, 1));
        notDestinations.add(new Cell(2, 10));
        notDestinations.add(new Cell(6, 6));
        notDestinations.add(new Cell(7, 10));
        notDestinations.add(new Cell(8, 9));
        for (Cell notDestination : notDestinations){
            assertFalse(solver.atDestination(notDestination));
        }

        Generator generatorB = new PrimGenerator();
        ArrayList<Cell> endPoints = new ArrayList<>();
        endPoints.add(generatorB.getGrid().getCell(4, 5));
        endPoints.add(generatorB.getGrid().getCell(15, 15));
        endPoints.add(generatorB.getGrid().getCell(0, 0));
        endPoints.add(generatorB.getGrid().getCell(11, 13));
        endPoints.add(generatorB.getGrid().getCell(7, 7));
        Cell startPoint = generatorB.getGrid().getCell(3, 4);
        Solver explicitSolver = new GreedyBestFirstSearchSolver(generatorB.getGrid(), startPoint, endPoints);

        destinations = new ArrayList<>();
        destinations.add(generatorB.getGrid().getCell(4, 5));
        destinations.add(generatorB.getGrid().getCell(15, 15));
        destinations.add(generatorB.getGrid().getCell(0, 0));
        destinations.add(generatorB.getGrid().getCell(11, 13));
        destinations.add(generatorB.getGrid().getCell(7, 7));

        notDestinations = new ArrayList<>();
        notDestinations.add(new Cell(5, 7));
        notDestinations.add(new Cell(3, 15));
        notDestinations.add(new Cell(1, 1));
        notDestinations.add(new Cell(2, 10));
        notDestinations.add(new Cell(6, 6));
        notDestinations.add(new Cell(7, 10));
        notDestinations.add(new Cell(8, 9));
        notDestinations.add(new Cell(8, 12));
        notDestinations.add(new Cell(14, 4));
        notDestinations.add(new Cell(0, 3));
        notDestinations.add(new Cell(2, 0));
        notDestinations.add(new Cell(8, 8));
        notDestinations.add(new Cell(8, 7));
        notDestinations.add(new Cell(7, 8));

        for (Cell destination : destinations){
            assertTrue(explicitSolver.atDestination(destination));
        }

        for (Cell notDestination : notDestinations){
            assertFalse(explicitSolver.atDestination(notDestination));
        }

    }
}