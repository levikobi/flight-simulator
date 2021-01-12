package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class HeightMapDisplayer extends Canvas {

    private int[][] grid;

    public HeightMapDisplayer() {

    }

    public void redraw() {
        if (grid == null) return;
        int m = grid.length, n = grid[0].length;

        double totalWidth = getWidth(), totalHeight = getHeight();
        double cellWidth = totalWidth / n, cellHeight = totalHeight / m;

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
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
        redraw();
    }
}
