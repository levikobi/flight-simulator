package view.components;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import view.library.HeightMap;
import viewmodel.ViewModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class HeightMapController {

    public ViewModel vm;

    @FXML public HeightMap heightMap;

    public ListProperty<String> grid;
    public StringProperty airplanePosition;
    public StringProperty destinationPosition;
    public StringProperty path;

    public void setViewModel(ViewModel vm) {
        this.vm = vm;

        grid = new SimpleListProperty<>();
        airplanePosition = new SimpleStringProperty();
        destinationPosition = new SimpleStringProperty();
        path = new SimpleStringProperty();

        vm.grid.bind(grid);
        airplanePosition.bind(vm.airplanePosition);
        vm.destinationPosition.bind(destinationPosition);
        path.bind(vm.path);

        airplanePosition.addListener(((observable, oldValue, newValue) -> {
            heightMap.setCharacterPosition(Arrays.stream(newValue.split(",")).mapToInt(Integer::parseInt).toArray());
        }));

        path.addListener(((observable, oldValue, newValue) -> {
            heightMap.setPath(path.get());
            System.out.println("Updated path");
        }));
    }

    public void parseMapFile(Path path) throws IOException {
        List<String> data = Files.readAllLines(path);
        vm.setMapCoordinatesAndScale(data.subList(0, 2));
        grid.setValue(FXCollections.observableArrayList(data.subList(2, data.size())));
        heightMap.setGrid(parseGrid(data.subList(2, data.size())));
    }

    private int[][] parseGrid(List<String> data) {
        int m = data.size(), n = data.get(0).split(",").length;
        int[][] grid = new int[m][n];
        for (int line = 0; line < m; line++) {
            String[] heights = data.get(line).split(",");
            for (int i = 0; i < n; i++) {
                grid[line][i] = Integer.parseInt(heights[i]);
            }
        }
        return grid;
    }

    public void handleClickMapDisplayer(MouseEvent mouseEvent) {
        heightMap.setDestinationPosition(mouseEvent);
        destinationPosition.set(heightMap.destinationPosition[0] + "," + heightMap.destinationPosition[1]);
    }
}
