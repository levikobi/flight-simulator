package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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

    @FXML public HeightMapDisplayer mapDisplayer;
    @FXML public TextArea textarea;

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
    }

    public void loadDataFile(ActionEvent actionEvent) throws IOException {
        Path path = openDataFilePath();
        if (path == null) return;
        List<String> data = Files.readAllLines(path);
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
        textarea.setText(read);
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
