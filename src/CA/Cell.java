package CA;


import javafx.scene.shape.Shape;

import java.util.List;

abstract public class Cell {
    private int currentState;
    private int nextState;
    private int row;
    private int col;
    private Shape myCellShape;


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

    public void setCurrentToNextState(){
        this.currentState = this.nextState;
    }

    public Shape getCellMyShape() {
        return myCellShape;
    }

    public void setCellShape(Shape shape) {
        myCellShape = shape;
    }

    public abstract void updateCell(List<Cell> neighbors);


    public int getNextState(){
        return nextState;
    }
}

