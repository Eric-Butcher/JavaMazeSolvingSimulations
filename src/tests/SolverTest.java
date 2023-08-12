package tests;

import model.Cell;
import model.generators.Generator;
import model.generators.PrimGenerator;
import model.solvers.GreedyBestFirstSearchSolver;
import model.solvers.Solver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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