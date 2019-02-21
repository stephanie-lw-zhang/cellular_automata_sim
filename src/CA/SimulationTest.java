package CA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    private static final String TEST_CONFIG_FILE_1="test1.csv";
    private static final int SCREEN_SIZE = 400;
    private Grid grid1;

    @BeforeEach
    public void setUp(){
        grid1 = new SquareGrid(TEST_CONFIG_FILE_1,SCREEN_SIZE);
    }

    @Test
    public void testSetCurrentToNextState(){
        var current = grid1.getMyGrid()[1][1].getCurrentState();
        grid1.update();
        assertEquals(current,grid1.getMyGrid()[1][1].getCurrentState());
    }

}