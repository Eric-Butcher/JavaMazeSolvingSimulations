package tests;

import model.Cell;
import model.generators.Generator;
import model.generators.PrimGenerator;
import model.solvers.GreedyBestFirstSearchSolver;
import model.solvers.Solver;
import org.junit.jupiter.api.Test;
import utilities.Constants;

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
    public void testGetAdjacentCells(){
        PrimGenerator primGenerator = new PrimGenerator();
        Solver solver = new GreedyBestFirstSearchSolver(primGenerator.getGrid());

        int cornerSize = 2;
        int sideSize = 3;
        int middleSize = 4;

        //Top left corner
        ArrayList<Cell> a = solver.getAdjacentCells(solver.getCell(Constants.minCellIndex, Constants.minCellIndex));
        assertEquals(cornerSize, a.size());
        assertTrue(a.contains(solver.getCell(Constants.minCellIndex, Constants.minCellIndex+1)));
        assertTrue(a.contains(solver.getCell(Constants.minCellIndex+1, Constants.minCellIndex)));

        //Top right corner
        ArrayList<Cell> b = solver.getAdjacentCells(solver.getCell(Constants.maxCellIndex, Constants.minCellIndex));
        assertEquals(cornerSize, b.size());
        assertTrue(b.contains(solver.getCell(Constants.maxCellIndex-1, Constants.minCellIndex)));
        assertTrue(b.contains(solver.getCell(Constants.maxCellIndex, Constants.minCellIndex+1)));

        //Bottom left corner
        ArrayList<Cell> c = solver.getAdjacentCells(solver.getCell(Constants.minCellIndex, Constants.maxCellIndex));
        assertEquals(cornerSize, c.size());
        assertTrue(c.contains(solver.getCell(Constants.minCellIndex+1, Constants.maxCellIndex)));
        assertTrue(c.contains(solver.getCell(Constants.minCellIndex, Constants.maxCellIndex-1)));

        //Bottom right corner
        ArrayList<Cell> d = solver.getAdjacentCells(solver.getCell(Constants.maxCellIndex, Constants.maxCellIndex));
        assertEquals(cornerSize, d.size());
        assertTrue(d.contains(solver.getCell(Constants.maxCellIndex-1, Constants.maxCellIndex)));
        assertTrue(d.contains(solver.getCell(Constants.maxCellIndex, Constants.maxCellIndex-1)));

        //Top side
        int middleCord = 7;
        ArrayList<Cell> e = solver.getAdjacentCells(solver.getCell(middleCord, Constants.minCellIndex));
        assertEquals(sideSize, e.size());
        assertTrue(e.contains(solver.getCell(middleCord-1, Constants.minCellIndex)));
        assertTrue(e.contains(solver.getCell(middleCord+1, Constants.minCellIndex)));
        assertTrue(e.contains(solver.getCell(middleCord, Constants.minCellIndex+1)));

        //Right side
        middleCord = 9;
        ArrayList<Cell> f = solver.getAdjacentCells(solver.getCell(Constants.maxCellIndex, middleCord));
        assertEquals(sideSize, f.size());
        assertTrue(f.contains(solver.getCell(Constants.maxCellIndex-1, middleCord)));
        assertTrue(f.contains(solver.getCell(Constants.maxCellIndex, middleCord+1)));
        assertTrue(f.contains(solver.getCell(Constants.maxCellIndex, middleCord-1)));

        //Bottom side
        middleCord = 4;
        ArrayList<Cell> g = solver.getAdjacentCells(solver.getCell(middleCord, Constants.maxCellIndex));
        assertEquals(sideSize, g.size());
        assertTrue(g.contains(solver.getCell(middleCord-1, Constants.maxCellIndex)));
        assertTrue(g.contains(solver.getCell(middleCord+1, Constants.maxCellIndex)));
        assertTrue(g.contains(solver.getCell(middleCord, Constants.maxCellIndex-1)));

        //Left side
        middleCord = 12;
        ArrayList<Cell> h = solver.getAdjacentCells(solver.getCell(Constants.minCellIndex, middleCord));
        assertEquals(sideSize, h.size());
        assertTrue(h.contains(solver.getCell(Constants.minCellIndex+1, middleCord)));
        assertTrue(h.contains(solver.getCell(Constants.minCellIndex, middleCord-1)));
        assertTrue(h.contains(solver.getCell(Constants.minCellIndex, middleCord+1)));

        // Somewhere in the middle
        middleCord = 3;
        ArrayList<Cell> i = solver.getAdjacentCells(solver.getCell(middleCord, middleCord));
        assertEquals(middleSize, i.size());
        assertTrue(i.contains(solver.getCell(middleCord+1, middleCord)));
        assertTrue(i.contains(solver.getCell(middleCord, middleCord+1)));
        assertTrue(i.contains(solver.getCell(middleCord-1, middleCord)));
        assertTrue(i.contains(solver.getCell(middleCord, middleCord-1)));

        // Somewhere else in the middle
        middleCord = 9;
        int otherMiddleCord = 14;
        ArrayList<Cell> j = solver.getAdjacentCells(solver.getCell(middleCord, otherMiddleCord));
        assertEquals(middleSize, j.size());
        assertTrue(j.contains(solver.getCell(middleCord-1, otherMiddleCord)));
        assertTrue(j.contains(solver.getCell(middleCord, otherMiddleCord-1)));
        assertTrue(j.contains(solver.getCell(middleCord+1, otherMiddleCord)));
        assertTrue(j.contains(solver.getCell(middleCord, otherMiddleCord+1)));
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
    public void testIsTherePathBetweenCells(){
        Cell a1 = new Cell(0, 1);
        Cell a2 = new Cell(1, 1);
        assertFalse(Solver.isTherePathBetweenCells(a1, a2));
        assertFalse(Solver.isTherePathBetweenCells(a2, a1));

        // To the right/left
        Cell b1 = new Cell(0, 1);
        b1.removeRightBorder();
        Cell b2 = new Cell(1, 1);
        b2.removeLeftBorder();
        assertTrue(Solver.isTherePathBetweenCells(b1, b2));
        assertTrue(Solver.isTherePathBetweenCells(b2, b1));

        // To the top/bottom
        Cell c1 = new Cell(4, 4);
        c1.removeBottomBorder();
        Cell c2 = new Cell(4, 5);
        c2.removeTopBorder();
        assertTrue(Solver.isTherePathBetweenCells(c1, c2));
        assertTrue(Solver.isTherePathBetweenCells(c2, c1));
    }

    @Test
    public void testGetUnTraversedReachableNeighbors(){
        int ax = 5;
        int ay = 5;
        Cell centerA = new Cell(ax, ay);
        centerA.removeTopBorder();
        centerA.removeRightBorder();

        Cell topA = new Cell(ax, ay-1);
        topA.removeBottomBorder();
        Cell rightA = new Cell(ax+1, ay);
        rightA.removeLeftBorder();
        rightA.setTraversed(true);
        Cell bottomA = new Cell(ax, ay+1);
        Cell leftA = new Cell(ax-1, ay);




        int bx = 7;
        int by = 10;
        Cell centerB = new Cell(bx, by);
        centerB.removeTopBorder();
        centerB.removeRightBorder();

        Cell topB = new Cell(bx, by-1);
        Cell rightB = new Cell(bx+1, by);
        Cell bottomB = new Cell(bx, by+1);
        Cell leftB = new Cell(bx-1, by);


        int cx = 3;
        int cy = 2;
        Cell centerC = new Cell(cx, cy);
        centerC.removeTopBorder();
        centerC.removeRightBorder();

        Cell topC = new Cell(cx, cy-1);
        Cell rightC = new Cell(cx+1, cy);
        Cell bottomC = new Cell(cx, cy+1);
        Cell leftC = new Cell(cx-1, cy);
    }

    @Test
    public void testAtDestination(){

    }
}