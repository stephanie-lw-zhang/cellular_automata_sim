import java.util.ArrayList;
import java.util.Arrays;

abstract public class Cell {
    private int currentState;
    private int nextState;
    private int row;
    private int col;


    public Cell(int state, int row, int col){
        this.currentState = state;
        this.row = row;
        this.col = col;
    }

    private void fillCell(){
        this.nextState = 1;
    }

    private void emptyCell(){
        this.nextState = 0;
    }

    private int getCurrentState(){
        return this.currentState;
    }

    public ArrayList<Integer> getLocation(){
        ArrayList<Integer> location = new ArrayList<>( Arrays.asList(this.row, this.col) );
        return location;
    }


//    public abstract ArrayList<Cell> getNeighbors();

    public abstract void updateCell();


}

