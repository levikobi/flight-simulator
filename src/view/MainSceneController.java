package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import viewmodel.ViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainSceneController implements Observer {

    private ViewModel vm;

    public StringProperty airplanePosition;

    @FXML public HeightMapDisplayer mapDisplayer;
    @FXML public TextArea autopilotScript;

    public void setViewModel(ViewModel vm) {
        this.vm = vm;

        vm.autopilotScript.bind(autopilotScript.textProperty());

        airplanePosition = new SimpleStringProperty();
        airplanePosition.bind(vm.airplanePosition);
        airplanePosition.addListener(((observable, oldValue, newValue) -> System.out.println("Pos: " + airplanePosition.get())));
    }

    public void loadDataFile(ActionEvent actionEvent) throws IOException {
        Path path = openDataFilePath();
        if (path == null) return;
        List<String> data = Files.readAllLines(path);
        vm.setMapCoordinatesAndScale(data.subList(0, 2));
        mapDisplayer.setGrid(parseGrid(data.subList(2, data.size())));
    }

    private Path openDataFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a map");
        fileChooser.setInitialDirectory(new File("./resources"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files","*.csv"));
        File chosenFile = fileChooser.showOpenDialog(null);
        return chosenFile == null ? null : chosenFile.toPath();
    }

    public void loadAutopilotScript(ActionEvent actionEvent) throws IOException {
        Path path = Paths.get("scripts/autopilot.txt");
        String read = String.join("\n", Files.readAllLines(path));
        autopilotScript.setText(read);
        vm.runAutopilotScript();
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

    public void openPopupScene(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("PopupScene.fxml"));
            Parent root = fxmlLoader.load();
            Stage popupWindowStage = new Stage();
            popupWindowStage.setTitle("Connect");
            popupWindowStage.setScene(new Scene(root));

            PopupSceneController psc = fxmlLoader.getController();
            psc.setViewModel(vm);
            vm.addObserver(psc);

            if (!popupWindowStage.isShowing()) {
                popupWindowStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
