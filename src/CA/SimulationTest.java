package CA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    private static final String TEST_CONFIG_FILE_1= "PercolationTest1.csv";
    private static final int SCREEN_SIZE = 400;
    private Grid PercolationSquareGrid;

    @BeforeEach
    public void setUp(){
        PercolationSquareGrid = new SquareGrid(TEST_CONFIG_FILE_1,SCREEN_SIZE);
    }

    @Test
    public void testSetCurrentToNextState(){
        var current = PercolationSquareGrid.getMyGrid()[1][1].getCurrentState();
        PercolationSquareGrid.update();
        assertEquals(current, PercolationSquareGrid.getMyGrid()[1][1].getCurrentState());
    }

    @Test
    public void testUpdateCellPercolationFill(){
        var expectedStartState = 0;
        var startState = PercolationSquareGrid.getMyGrid()[1][0].getCurrentState();
        assertEquals(expectedStartState,startState);
        PercolationSquareGrid.update();
        var expectedNextState = 1;
        assertEquals(expectedNextState,PercolationSquareGrid.getMyGrid()[1][0].getCurrentState());
    }

    @Test
    public void testUpdateCellPercolationStayBlocked(){
        var blockedGrid = new SquareGrid("PercolationTest2.csv",SCREEN_SIZE);
        var blockedState = -1;
        assertEquals(-1, blockedGrid.getMyGrid()[1][1].getCurrentState());
        blockedGrid.getMyGrid()[1][1].updateCell(blockedGrid.getNeighbors(1,1));
        assertEquals(-1, blockedGrid.getMyGrid()[1][1].getCurrentState());
    }

    @Test
    public void testGetNeighborSquareGridNormal(){
        var rows = PercolationSquareGrid.getMyGrid().length;
        var cols = PercolationSquareGrid.getMyGrid()[0].length;

        for(int iterRow=1;iterRow<rows-1;iterRow++){
            for(int iterCol=1;iterCol<cols-1;iterCol++){
                List<Cell> neighborList = PercolationSquareGrid.getNeighbors(iterRow,iterCol);
                assertEquals(8, neighborList.size());
                testGetNeighborSquareGridHelper(rows, cols, iterRow, iterCol, neighborList);
            }
        }
    }

    @Test
    public void testGetNeighborSquareGridEdgeCases(){
        var rows = PercolationSquareGrid.getMyGrid().length;
        var cols = PercolationSquareGrid.getMyGrid()[0].length;
        //rows
        for(int edgeCol=0;edgeCol<cols;edgeCol++){
            int squareI = 0;
            int squareJ = edgeCol;
            List<Cell> neighborList = PercolationSquareGrid.getNeighbors(squareI,squareJ);
            testGetNeighborSquareGridHelper(rows, cols, squareI, squareJ, neighborList);
        }
        //cols
//        for(int edgeRow=0;edgeRow<rows;edgeRow++){
//
//        }

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

//    private int testGetNeighborReturnExpectedNeighbors(Grid grid){
//        if(grid.getClass().isInstance()){
//
//        }
//    }
}