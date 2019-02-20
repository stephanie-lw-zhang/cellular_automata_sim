package CA;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Scanner;

abstract public class Grid {
    private String myGame;
    private int myRow, myCol;
    private double myCellWidth, myCellHeight, currentX, currentY;
    private Cell[][] myGrid;
    private double gridSize;

    public Grid(String source, double size) {
        currentX = 0.0;
        currentY = 0.0;
        gridSize = size;

        Scanner sc = new Scanner(Grid.class.getClassLoader().getResourceAsStream(source));
        myGame = sc.nextLine();
        String[] sizes = sc.nextLine().split(",");
        myRow = Integer.parseInt(sizes[0]);
        myCol = Integer.parseInt(sizes[1]);
        myGrid = new Cell[myRow][myCol];
        int row = 0;
        int col = 0;
        sc.useDelimiter(",");
        while (sc.hasNextLine()) {
            String[] states = sc.nextLine().split(",");
            for (String str : states) {
                myGrid[row][col] = cellType(Integer.parseInt(str), row, col);
                col++;
            }
            col = 0;
            row++;
        }
    }

    private Cell cellType(int state, int row, int col) {
        if (myGame.equals("Game Of Life")) {
            return new GameOfLifeCell(state,row,col);
        }
        else if (myGame.equals("Percolation")) {
            return new PercolationCell(state,row,col);
        }
        return null;
    }

    public void addUpdatedToScene() {
        for (int i = 0; i < getMyRow(); i++) {
            for (int j = 0; j < getMyCol(); j++) {
                addColorToScene(i, j);
            }
        }
    }

    public void addColorToScene(int i, int j) {
        if (myGrid[i][j].getCurrentState() == 1) {
            myGrid[i][j].getCellMyShape().setFill(Color.web("#008ecc"));
        }
        else if (myGrid[i][j].getCurrentState() == 0) {
            myGrid[i][j].getCellMyShape().setFill(Color.web("#FFFFFF"));
        }
        else {
            myGrid[i][j].getCellMyShape().setFill(Color.web("#000000"));
        }
    }

    public abstract double calcCellWidth();

    public abstract double calcCellHeight();

    public abstract void update();

    public abstract List<Cell> getNeighbors(int row, int col);

    public abstract void addToScene(Group myRoot);

    public void setMyCellWidth(double num){
        myCellWidth = num;
    }

    public void setMyCellHeight(double num){
        myCellHeight = num;
    }

    public int getMyRow(){
        return myRow;
    }

    public int getMyCol(){
        return myCol;
    }

    public Cell[][] getMyGrid(){
        return myGrid;
    }

    public double getGridSize(){
        return this.gridSize;
    }

    public double getMyCellWidth() {
        return myCellWidth;
    }

    public double getMyCellHeight() {
        return myCellHeight;
    }

    public double getCurrentX() {
        return currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentX(double x) {
        currentX = x;
    }

    public void setCurrentY(double y) {
        currentY = y;
    }
}
