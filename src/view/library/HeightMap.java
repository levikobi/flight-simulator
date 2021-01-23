package view.library;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HeightMap extends Canvas {

    private final StringProperty characterImageName;
    private final StringProperty destinationImageName;
    private Image characterImage;
    private Image destinationImage;

    private final GraphicsContext graphicsContext;

    public int[][] grid;
    public int[] characterPosition;
    public int[] destinationPosition;
    public String path;

    private int m, n, minHeight, maxHeight;
    private double cellW;
    private double cellH;

    private static final String LEFT = "Left";
    private static final String RIGHT = "Right";
    private static final String UP = "Up";
    private static final String DOWN = "Down";

    public HeightMap() {
        characterImageName = new SimpleStringProperty();
        destinationImageName = new SimpleStringProperty();
        graphicsContext = getGraphicsContext2D();
    }

    public void redraw() {
        if (grid == null) return;
        m = grid.length;
        n = grid[0].length;

        double totalWidth = getWidth(), totalHeight = getHeight();
        cellW = totalWidth / n;
        cellH = totalHeight / m;

        Platform.runLater(() -> {
            graphicsContext.clearRect(0, 0, totalWidth, totalHeight);
            drawGrid();
            drawPath();
            drawCharacter();
            drawDestination();
        });
    }

    private void drawGrid() {
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                graphicsContext.setFill(getColorFromHeight(grid[row][col]));
                graphicsContext.fillRect(col* cellW, row* cellH, cellW, cellH);
            }
        }
    }

    private Color getColorFromHeight(double h){
        int red = 200, green = 200, blue = 0;
        double marg = (maxHeight - minHeight)/2 + minHeight;
        double partDis = Math.abs((h - marg))/(maxHeight - marg);
        if (h < marg) {
            green *= (1 - partDis);
        } else {
            red *= (1 - partDis);
        }
        return Color.rgb(red, green, blue, 0.5);
    }

    private void drawPath() {
        if (path == null || !inBounds(characterPosition)) return;
        int i = 0, y = characterPosition[0], x = characterPosition[1];
        String[] directions = path.split(",");
        for (String direction : directions) {
            switch (direction) {
                case LEFT:  x--;    break;
                case RIGHT: x++;    break;
                case UP:    y--;    break;
                case DOWN:  y++;    break;
                default:            break;
            }
            if ((i=++i%10) < 3) continue;
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(x * cellW, y * cellH, cellW, cellH);
        }
    }

    private void drawCharacter() {
        try {
            characterImage = new Image(new FileInputStream(characterImageName.get()));
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        if (characterPosition == null || !inBounds(characterPosition)) return;
        graphicsContext.drawImage(characterImage,
                cellW * (characterPosition[1] - 7.5),
                cellH * (characterPosition[0] - 7.5),
                15 * cellW, 15 * cellH);
    }

    private void drawDestination() {
        try {
            destinationImage = new Image(new FileInputStream(destinationImageName.get()));
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        if (destinationPosition == null || !inBounds(destinationPosition)) return;
        graphicsContext.drawImage(destinationImage,
                cellW * (destinationPosition[1] - 4),
                cellH * (destinationPosition[0] - 4),
                8 * cellW, 8 * cellH);
    }

    private boolean inBounds(int[] position) {
        return position[0] >= 0 && position[1] >= 0 && position[0] < m && position[1] < n;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
        redraw();
    }

    public void setHeights(int[] heights) {
        this.minHeight = heights[0];
        this.maxHeight = heights[1];
    }

    public void setCharacterPosition(int[] characterPosition) {
        this.characterPosition = characterPosition;
        if (grid != null && inBounds(characterPosition)) redraw();
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
        setDestinationPosition(new int[] {(int)(y/ cellH), (int) (x/ cellW)});
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
