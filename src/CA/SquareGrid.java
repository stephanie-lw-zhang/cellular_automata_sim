package CA;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareGrid extends Grid {
    public SquareGrid(String source, double size) {
        super(source, size);
    }

    public void addToScene(Group myRoot) {
        for (int i = 0; i < myRow; i++) {
            for (int j = 0; j < myCol; j++) {
                Rectangle cell = new Rectangle();
                if (myGrid[i][j].getCurrentState() == 1) {
                    cell.setFill(Color.web("#ed4b00"));
                }
                cell.setHeight(myCellHeight);
                cell.setWidth(myCellWidth);
                cell.setX(currentX);
                cell.setY(currentY);
                myRoot.getChildren().add(cell);
                currentX += myCellWidth;
            }
            currentY += myCellHeight;
        }
    }

    public void calcCellLength() {
        myCellWidth = gridSize/myCol;
    }

    public void calcCellHeight() {
        myCellHeight = gridSize/myRow;
    }
}
