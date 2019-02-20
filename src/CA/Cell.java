package CA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class Cell {
    private int currentState;
    private int nextState;
    private int row;
    private int col;

    public Cell(int state, int row, int col){
        this.currentState = state;
        this.nextState = state;
        this.row = row;
        this.col = col;
    }

    public void fillCell(){
        this.nextState = 1;
    }

    public void emptyCell(){
        this.nextState = 0;
    }

    public int getCurrentState(){
        return this.currentState;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

//    public abstract ArrayList<Cell> getNeighbors();

    public abstract Cell updateCell(List<Cell> neighbors);


}

