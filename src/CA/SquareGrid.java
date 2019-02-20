package CA;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class SquareGrid extends Grid {
    public SquareGrid(String source, double size) {
        super(source, size);
        myCellWidth = calcCellWidth();
        myCellHeight = calcCellHeight();
    }

    public void addToScene(Group myRoot) {
        for (int i = 0; i < myRow; i++) {
            for (int j = 0; j < myCol; j++) {
                myCellShape = new Rectangle(currentX, currentY, myCellWidth, myCellHeight);
                if (myGrid[i][j].getCurrentState() == 1) {
                    myCellShape.setFill(Color.web("#008ecc"));
                }
                myRoot.getChildren().add(myCellShape);
                currentX += myCellWidth;
            }
            currentX = 0;
            currentY += myCellHeight;
        }
    }

    public void update() {
        for (int i = 0; i < myRow; i++) {
            for (int j = 0; j < myCol; j++) {
                myGrid[i][j].updateCell(getNeighbors(i,j));
            }
        }
    }

    public List<Cell> getNeighbors(int row, int col) {
        List<Cell> neighborCells = new ArrayList<>();
        if (row != myRow - 1) {
            neighborCells.add(myGrid[row+1][col]);
            if (col == 0) { neighborCells.add(myGrid[row+1][myCol -1]); }
            else if (col != 0) { neighborCells.add(myGrid[row+1][col - 1]); }
            if (col == myCol - 1) { neighborCells.add(myGrid[row+1][0]); }
            else if (col != myCol - 1) { neighborCells.add(myGrid[row+1][col + 1]); }
        }
        else if (row == myRow - 1) {
            neighborCells.add(myGrid[0][col]);
            if (col == 0) { neighborCells.add(myGrid[0][myCol -1]); }
            else if (col != 0) { neighborCells.add(myGrid[0][col - 1]); }
            if (col == myCol -1) { neighborCells.add(myGrid[0][0]); }
            else if (col != myCol - 1) { neighborCells.add(myGrid[0][col + 1]); }
        }
        if (row != 0) {
            neighborCells.add(myGrid[row-1][col]);
            if (col == 0) { neighborCells.add(myGrid[row-1][myCol -1]); }
            else if (col != 0) { neighborCells.add(myGrid[row-1][col - 1]); }
            if (col == myCol - 1) { neighborCells.add(myGrid[row-1][0]); }
            else if (col != myCol - 1) { neighborCells.add(myGrid[row-1][col + 1]); }
        }
        else if (row == 0) {
            neighborCells.add(myGrid[myRow-1][col]);
            if (col == 0) { neighborCells.add(myGrid[myRow-1][myCol - 1]); }
            else if (col != 0) { neighborCells.add(myGrid[myRow-1][col - 1]); }
            if (col == myCol - 1) { neighborCells.add(myGrid[myRow-1][0]); }
            else if (col != myCol - 1) { neighborCells.add(myGrid[myRow-1][col + 1]); }
        }
        if (col != 0) { neighborCells.add(myGrid[row][col - 1]); }
        else if (col == 0) { neighborCells.add(myGrid[row][myCol - 1]); }
        if (col != myCol - 1) { neighborCells.add(myGrid[row][col + 1]); }
        else if (col == myCol -1 ) { neighborCells.add(myGrid[row][0]); }
        return neighborCells;
    }

    public double calcCellWidth() {
        return gridSize/myCol;
    }

    public double calcCellHeight() {
        return gridSize/myRow;
    }
}
