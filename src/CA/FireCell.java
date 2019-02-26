package CA;

import java.util.List;

public class FireCell extends Cell {
    private int treeState = 1;
    private int burnState = 2;
    private double probCatch;

    public FireCell(int state, int row, int col) {
        super( state, row, col );
    }

    public void setProbCatch(int prob){
        if(prob < 0 || prob > 1){
            System.out.println("Set probability between 0 and 1 (inclusive).");
        }
        this.probCatch = prob;
    }


    @Override
    public void updateCell(List<Cell> neighbors) {
        Boolean neighborBurning = Boolean.FALSE;
        for(Cell each: neighbors){
            if(each.getCurrentState() == burnState){
                neighborBurning = Boolean.TRUE;
            }
        }
        if(this.getCurrentState() == treeState && neighborBurning){
            if(Math.random() < probCatch){
                this.fillCell( burnState );
            }
        }
    }
}
