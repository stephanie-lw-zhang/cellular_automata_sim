package CA;

import java.util.List;

public class GameOfLifeCell extends Cell{
    private int fillState;

    public GameOfLifeCell(int state, int row, int col){
        super(state, row, col);
    }


    @Override
    public void updateCell(List<Cell> neighbors) {
        int numNeighborsAlive = 0;
        for(Cell each: neighbors){
            if(each.getCurrentState() == 1){
                numNeighborsAlive++;
            }
        }
        if(this.getCurrentState() == 1){
            if(numNeighborsAlive < 2 || numNeighborsAlive > 3){
                this.emptyCell();
            }
            else if (numNeighborsAlive == 2 || numNeighborsAlive == 3){
                this.fillCell(fillState);
            }
        }
        else if (this.getCurrentState() == 0){
            if(numNeighborsAlive == 3){
                this.fillCell(fillState);
            }
        }
    }
}
