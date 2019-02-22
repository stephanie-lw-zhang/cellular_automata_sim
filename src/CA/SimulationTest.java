package CA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {
    private static final String PERC_TEST_CONFIG_FILE_1= "PercolationTest1.csv";
    private static final int SCREEN_SIZE = 400;
    private static final String TEST_CONFIG_FILE_1="test1.csv";
    private static final String TEST_CONFIG_FILE_2="test2.csv";
    private static final String TEST_CONFIG_FILE_3="test3.csv";
    private static final String TEST_CONFIG_FILE_4="test4.csv";
    private static final int SCREEN_SIZE_2 = 600;
    private Grid grid1;
    private Grid grid2;
    private Grid grid3;
    private Grid grid4;
    private Grid grid5;

    @BeforeEach
    public void setUp(){
        grid1 = new SquareGrid(TEST_CONFIG_FILE_1,SCREEN_SIZE,SCREEN_SIZE);
        grid2 = new SquareGrid(TEST_CONFIG_FILE_2,SCREEN_SIZE,SCREEN_SIZE);
        grid3 = new SquareGrid(TEST_CONFIG_FILE_3,SCREEN_SIZE_2,SCREEN_SIZE_2);
        grid4 = new SquareGrid(TEST_CONFIG_FILE_2,SCREEN_SIZE,SCREEN_SIZE_2);
        grid5 = new SquareGrid(TEST_CONFIG_FILE_4,SCREEN_SIZE,SCREEN_SIZE);
    }

    @Test
    public void testSetCurrentToNextState(){
        var current = grid1.getMyGrid()[1][1].getCurrentState();
        grid1.update();
        assertEquals(current, grid1.getMyGrid()[1][1].getCurrentState());
    }

    @Test
    public void testUpdateCellPercolationFill(){
        var expectedStartState = 0;
        var startState = grid1.getMyGrid()[1][0].getCurrentState();
        assertEquals(expectedStartState,startState);
        grid1.update();
        var expectedNextState = 1;
        assertEquals(expectedNextState,grid1.getMyGrid()[1][0].getCurrentState());
    }

    @Test
    public void testUpdateCellPercolationStayBlocked(){
        var blockedGrid = new SquareGrid(PERC_TEST_CONFIG_FILE_1,SCREEN_SIZE,SCREEN_SIZE);
        var blockedState = -1;
        assertEquals(-1, blockedGrid.getMyGrid()[1][1].getCurrentState());
        blockedGrid.getMyGrid()[1][1].updateCell(blockedGrid.getNeighbors(1,1));
        assertEquals(-1, blockedGrid.getMyGrid()[1][1].getCurrentState());
    }

    @Test
    public void testGetNeighborSquareGridNormal(){
        var rows = grid1.getMyGrid().length;
        var cols = grid1.getMyGrid()[0].length;

        for(int iterRow=1;iterRow<rows-1;iterRow++){
            for(int iterCol=1;iterCol<cols-1;iterCol++){
                List<Cell> neighborList = grid1.getNeighbors(iterRow,iterCol);
                assertEquals(8, neighborList.size());
                testGetNeighborSquareGridHelper(rows, cols, iterRow, iterCol, neighborList);
            }
        }
    }

    @Test
    public void testGetNeighborSquareGridEdgeCases(){
        var rows = grid1.getMyGrid().length;
        var cols = grid1.getMyGrid()[0].length;
        //rows
        for(int edgeCol=0;edgeCol<cols;edgeCol++){
            List<Cell> neighborList = grid1.getNeighbors(0,edgeCol);
            testGetNeighborSquareGridHelper(rows, cols, 0, edgeCol, neighborList);
            neighborList = grid1.getNeighbors(rows-1,edgeCol);
            testGetNeighborSquareGridHelper(rows, cols, rows-1, edgeCol, neighborList);
        }
        //cols
        for(int edgeRow=0;edgeRow<rows;edgeRow++){
            List<Cell> neighborList = grid1.getNeighbors(edgeRow,0);
            testGetNeighborSquareGridHelper(rows, cols, edgeRow, 0, neighborList);
            neighborList = grid1.getNeighbors(edgeRow,cols-1);
            testGetNeighborSquareGridHelper(rows, cols, edgeRow, cols-1, neighborList);
        }

    }

    private void testGetNeighborSquareGridHelper(int rows, int cols, int squareI, int squareJ, List<Cell> neighborList) {
        for(int i=squareI-1;i<=squareI+1;i++){
            for(int j=squareJ-1;j<=squareJ+1;j++){
                int currentI;
                int currentJ;
                if(i<0){
                    currentI=i+rows;
                } else if (i>=rows){
                    currentI=i-rows;
                } else{
                    currentI=i;
                }
                if(j<0){
                    currentJ=j+cols;
                } else if (j>=cols){
                    currentJ=j-cols;
                } else{
                    currentJ=j;
                }
                if(!(currentI==squareI&&currentJ==squareJ)) {
                    neighborList.removeIf(c -> (c.getRow() == currentI && c.getCol() == currentJ));
                }
            }
        }
        assertEquals(0,neighborList.size());
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

    @Test
    public void testCalculationCellWidth() {
        assertEquals(400/3.0, grid1.calcCellWidth());
        assertEquals(400/3.0, grid4.calcCellWidth());
    }

    @Test
    public void testCalculationCellHeight() {
        assertEquals(400/3.0, grid1.calcCellHeight());
        assertEquals(200, grid4.calcCellHeight());
    }

    @Test
    public void testCellDimensionsNoCells() {
        assertEquals(0, grid3.calcCellWidth());
        assertEquals(0, grid3.calcCellHeight());
    }

    @Test
    public void testGridUpdateWithPercolation() {
        boolean allFilled = true;
        grid1.update();
        for (int i = 0; i < grid1.getMyRow(); i++) {
            for(int j = 0; j < grid1.getMyCol(); j++) {
                if (grid1.getMyGrid()[i][j].getCurrentState() == 0) {
                    allFilled = false;
                }
            }
        }
        assertTrue(allFilled);
    }

    @Test
    public void testGridUpdateWithGameOfLife() {
        boolean colFilled = true;
        grid5.getMyGrid()[2][1].fillCellCurrent();
        grid5.getMyGrid()[2][2].fillCellCurrent();
        grid5.getMyGrid()[2][3].fillCellCurrent();
        grid5.update();
        for (int i = 1; i < 3; i++) {
            if (grid5.getMyGrid()[i][2].getCurrentState() == 0) {
                colFilled = false;
            }
        }
        assertTrue(colFilled);
    }
}
