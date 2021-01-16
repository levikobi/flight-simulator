package view.library;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HeightMap extends Canvas {

    private StringProperty characterImageName;
    private StringProperty destinationImageName;

    public int[][] grid;
    public int[] characterPosition;
    public int[] destinationPosition;
    public String path;

    private Image characterImage;
    private Image destinationImage;

    private double cellWidth;
    private double cellHeight;

    public HeightMap() {
        characterImageName = new SimpleStringProperty();
        destinationImageName = new SimpleStringProperty();
    }

    public void redraw() {
        if (grid == null) return;
        int m = grid.length, n = grid[0].length;

        double totalWidth = getWidth(), totalHeight = getHeight();
        cellWidth = totalWidth / n;
        cellHeight = totalHeight / m;

        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, totalWidth, totalHeight);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] > 500) graphicsContext.setFill(Paint.valueOf("64e291"));
                else if (grid[row][col] > 250) graphicsContext.setFill(Paint.valueOf("e6e56c"));
                else if (grid[row][col] > 0) graphicsContext.setFill(Paint.valueOf("fec771"));
                else graphicsContext.setFill(Paint.valueOf("eb7070"));

                graphicsContext.fillRect(col*cellWidth, row*cellHeight, cellWidth, cellHeight);
            }
        }

        characterImage = null;
        try {
            characterImage = new Image(new FileInputStream(characterImageName.get()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (characterPosition != null && inBounds(characterPosition, m, n)) {
            graphicsContext.drawImage(characterImage,characterPosition[0], characterPosition[1], 15*cellWidth, 15*cellHeight);
        }

        destinationImage = null;
        try {
            destinationImage = new Image(new FileInputStream(destinationImageName.get()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (destinationPosition != null && inBounds(destinationPosition, m, n)) {
            graphicsContext.drawImage(destinationImage,
                    cellWidth * (destinationPosition[1] - 5),
                    cellHeight * (destinationPosition[0] - 5),
                    10 * cellWidth, 10 * cellHeight);
        }
    }

    private boolean inBounds(int[] position, int m, int n) {
        return position[0] >= 0 && position[1] >= 0 && position[0] < m && position[1] < n;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
        redraw();
    }

    public void setCharacterPosition(int[] characterPosition) {
        this.characterPosition = characterPosition;
        redraw();
    }

    public String getCharacterImageName() {
        return characterImageName.get();
    }

    public void setCharacterImageName(String characterImageName) {
        this.characterImageName.set(characterImageName);
    }

    public void setDestinationPosition(int[] destinationPosition) {
        this.destinationPosition = destinationPosition;
        redraw();
    }

    public void setDestinationPosition(MouseEvent mouseEvent) {
        double x = mouseEvent.getX(), y = mouseEvent.getY();
        setDestinationPosition(new int[] {(int)(y/cellHeight), (int) (x/cellWidth)});
    }

    public String getDestinationImageName() {
        return destinationImageName.get();
    }

    public void setDestinationImageName(String destinationImageName) {
        this.destinationImageName.set(destinationImageName);
    }

    public void setPath(String path) {
        this.path = path;
        redraw();
    }
}
