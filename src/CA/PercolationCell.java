package CA;

import java.util.List;

public class PercolationCell extends Cell {

    public PercolationCell(int state, int row, int col){
        super(state, row, col);
    }
    @Override
    public Cell updateCell(List<Cell> neighbors) {
        if(this.getCurrentState()==-1){
            return null;
        } else{
            for(Cell each: neighbors){
                if(each.getCurrentState()==1){
                    fillCell();
                    break;
                }
            }
        }
        return this;
    }
}
