package CA;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class SquareGrid extends Grid {
    public SquareGrid(String source, double size) {
        super(source, size);
        setMyCellWidth(calcCellWidth());
        setMyCellHeight(calcCellHeight());
    }

    public void addToScene(Group myRoot) {
        for (int i = 0; i < getMyRow(); i++) {
            for (int j = 0; j < getMyCol(); j++) {
                getMyGrid()[i][j].setCellShape(new Rectangle(getCurrentX(),getCurrentY(),getMyCellWidth(),getMyCellHeight()));
                getMyGrid()[i][j].getCellMyShape().setStroke(Color.web("#000000"));
                addColorToScene(i, j);
                myRoot.getChildren().add(getMyGrid()[i][j].getCellMyShape());
                double tempX = getCurrentX() + getMyCellWidth();
                setCurrentX(tempX);
            }
            setCurrentX(0);
            double tempY = getCurrentY() + getMyCellHeight();
            setCurrentY(tempY);
        }
    }

    public void update() {
        for (int i = 0; i < getMyRow(); i++) {
            for (int j = 0; j < getMyCol(); j++) {
                getMyGrid()[i][j].updateCell(getNeighbors(i,j));
            }
        }
        for (int i = 0; i < getMyRow(); i++) {
            for (int j = 0; j < getMyCol(); j++) {
                getMyGrid()[i][j].setCurrentToNextState();
            }
        }
    }

    public List<Cell> getNeighbors(int row, int col) {
        List<Cell> neighborCells = new ArrayList<>();
        if (row != getMyRow() - 1) {
            neighborHelper( col, neighborCells, row+1 );
        }
        else if (row == getMyRow() - 1) {
            neighborHelper( col, neighborCells, 0 );
        }
        if (row != 0) {
            neighborHelper( col, neighborCells, row - 1 );
        }
        else if (row == 0) {
            neighborHelper( col, neighborCells, getMyRow()-1 );
        }
        if (col != 0) { neighborCells.add(getMyGrid()[row][col - 1]); }
        else if (col == 0) { neighborCells.add(getMyGrid()[row][getMyCol() - 1]); }
        if (col != getMyCol() - 1) { neighborCells.add(getMyGrid()[row][col + 1]); }
        else if (col == getMyCol() -1 ) { neighborCells.add(getMyGrid()[row][0]); }
        return neighborCells;
    }

    private void neighborHelper(int col, List<Cell> neighborCells, int i) {
        neighborCells.add( getMyGrid()[i][col] );
        if (col == 0) {
            neighborCells.add( getMyGrid()[i][getMyCol() - 1] );
        } else if (col != 0) {
            neighborCells.add( getMyGrid()[i][col - 1] );
        }
        if (col == getMyCol() - 1) {
            neighborCells.add( getMyGrid()[i][0] );
        } else if (col != getMyCol() - 1) {
            neighborCells.add( getMyGrid()[i][col + 1] );
        }
    }

    public double calcCellWidth() {
        return getGridSize()/getMyCol();
    }

    public double calcCellHeight() {
        return getGridSize()/getMyRow();
    }

}
