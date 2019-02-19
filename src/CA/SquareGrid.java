package CA;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

    public double calcCellWidth() {
        return gridSize/myCol;
    }

    public double calcCellHeight() {
        return gridSize/myRow;
    }
}
