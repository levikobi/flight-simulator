package view.components;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public void setViewModel(ViewModel vm) {
        this.vm = vm;

        airplanePosition = new SimpleStringProperty();
        destinationPosition = new SimpleStringProperty();

        airplanePosition.bind(vm.airplanePosition);
        vm.destinationPosition.bind(destinationPosition);

        airplanePosition.addListener(((observable, oldValue, newValue) -> {
            heightMap.setCharacterPosition(Arrays.stream(newValue.split(",")).mapToInt(Integer::parseInt).toArray());
        }));
    }

    public void parseMapFile(Path path) throws IOException {
        List<String> data = Files.readAllLines(path);
        vm.setMapCoordinatesAndScale(data.subList(0, 2));
        ObservableList<String> observableList = FXCollections.observableArrayList(data.subList(2, data.size()));
        grid = new SimpleListProperty<>(observableList);
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
