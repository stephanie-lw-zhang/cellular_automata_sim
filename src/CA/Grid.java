package CA;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

import java.util.Scanner;

abstract class Grid {
    String myGame;
    int myRow, myCol;
    double myCellWidth, myCellHeight, currentX, currentY;
    Cell[][] myGrid;
    double gridSize;
    Shape myCellShape;

    public Grid(String source, double size) {
        currentX = 0;
        currentY = 0;
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
            return new GameOfLife(state,row,col);
        }
        else if (myGame.equals("Percolation")) {
            //return new Percolation(state,row,col);
        }
        return null;
    }

    abstract double calcCellWidth();

    abstract double calcCellHeight();

    public void update() {

    }

    abstract void addToScene(Group myRoot);


}
