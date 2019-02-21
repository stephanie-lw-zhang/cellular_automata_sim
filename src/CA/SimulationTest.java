package CA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    private static final String TEST_CONFIG_FILE_1="test1.csv";
    private static final String TEST_CONFIG_FILE_2="test2.csv";
    private static final int SCREEN_SIZE = 400;
    private Grid grid1;
    private Grid grid2;

    @BeforeEach
    public void setUp(){

        grid1 = new SquareGrid(TEST_CONFIG_FILE_1,SCREEN_SIZE);
        grid2 = new SquareGrid(TEST_CONFIG_FILE_2,SCREEN_SIZE);

    }

    @Test
    public void testSetCurrentToNextState(){
        var current = grid1.getMyGrid()[1][1].getCurrentState();
        grid1.update();
        assertEquals(current,grid1.getMyGrid()[1][1].getCurrentState());
    }

    @Test
    public void testFillCell(){
        grid1.getMyGrid()[0][0].fillCell();
        int expected = 1;
        assertEquals( expected, grid1.getMyGrid()[0][0].getNextState() );
    }

    @Test
    public void testEmptyCell(){
        grid1.getMyGrid()[1][1].emptyCell();
        int expected = 0;
        assertEquals( expected, grid1.getMyGrid()[1][1].getNextState() );
    }

    @Test
    public void testGameOfLifeUpdateUnderpopulation(){
        grid2.getMyGrid()[1][1].fillCellCurrent();
        grid2.getMyGrid()[1][1].updateCell(grid2.getNeighbors(1,1));
        grid2.getMyGrid()[1][1].setCurrentToNextState();
        int expected = 0;
        assertEquals( expected, grid2.getMyGrid()[1][1].getCurrentState() );
    }

    @Test
    public void testGameOfLifeUpdateNextGen(){
        grid2.getMyGrid()[1][1].fillCellCurrent();
        grid2.getMyGrid()[1][0].fillCellCurrent();
        grid2.getMyGrid()[1][2].fillCellCurrent();
        for (int i = 0; i < grid2.getMyRow(); i++) {
            for (int j = 0; j < grid2.getMyCol(); j++) {
                grid2.getMyGrid()[i][j].updateCell(grid2.getNeighbors(i,j));
            }
        }
        for (int i = 0; i < grid2.getMyRow(); i++) {
            for (int j = 0; j < grid2.getMyCol(); j++) {
                grid2.getMyGrid()[i][j].setCurrentToNextState();
            }
        }
        int expected = 1;
        assertEquals(expected, grid2.getMyGrid()[1][1].getCurrentState());
    }

    @Test
    public void testGameOfLifeUpdateOverpopulation(){
        grid2.getMyGrid()[1][1].fillCellCurrent();
        grid2.getMyGrid()[1][0].fillCellCurrent();
        grid2.getMyGrid()[1][2].fillCellCurrent();
        grid2.getMyGrid()[0][0].fillCellCurrent();
        grid2.getMyGrid()[2][2].fillCellCurrent();
        for (int i = 0; i < grid2.getMyRow(); i++) {
            for (int j = 0; j < grid2.getMyCol(); j++) {
                grid2.getMyGrid()[i][j].updateCell(grid2.getNeighbors(i,j));
            }
        }
        for (int i = 0; i < grid2.getMyRow(); i++) {
            for (int j = 0; j < grid2.getMyCol(); j++) {
                grid2.getMyGrid()[i][j].setCurrentToNextState();
            }
        }
        int expected = 0;
        assertEquals(expected, grid2.getMyGrid()[1][1].getCurrentState());
    }

    @Test
    public void testGameOfLifeUpdateReproduction(){
        grid2.getMyGrid()[0][2].fillCellCurrent();
        grid2.getMyGrid()[2][1].fillCellCurrent();
        grid2.getMyGrid()[0][0].fillCellCurrent();
        for (int i = 0; i < grid2.getMyRow(); i++) {
            for (int j = 0; j < grid2.getMyCol(); j++) {
                grid2.getMyGrid()[i][j].updateCell(grid2.getNeighbors(i,j));
            }
        }
        for (int i = 0; i < grid2.getMyRow(); i++) {
            for (int j = 0; j < grid2.getMyCol(); j++) {
                grid2.getMyGrid()[i][j].setCurrentToNextState();
            }
        }
        int expected = 1;
        assertEquals(expected, grid2.getMyGrid()[1][1].getCurrentState());
    }


}