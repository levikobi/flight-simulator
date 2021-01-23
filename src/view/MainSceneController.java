package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.components.HeightMapController;
import view.components.JoystickController;
import viewmodel.ViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainSceneController {

    private ViewModel vm;

    @FXML public TextArea autopilotScript;

    @FXML public Parent heightMap;
    @FXML public HeightMapController heightMapController;

    @FXML public Parent joystick;
    @FXML public JoystickController joystickController;

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
        heightMapController.setViewModel(vm);
        joystickController.setViewModel(vm);

        vm.autopilotScript.bind(autopilotScript.textProperty());
    }

    public void loadDataFile(ActionEvent actionEvent) throws IOException {
        Path path = openFilePath("Select a map", "./resources","CSV Files","*.csv");
        if (path == null) return;
        heightMapController.parseMapFile(path);
    }

    private Path openFilePath(String title, String pathname, String description, String extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(new File(pathname));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(description, extensions));
        File chosenFile = fileChooser.showOpenDialog(null);
        return chosenFile == null ? null : chosenFile.toPath();
    }

    public void runAutopilotScript(ActionEvent actionEvent) throws IOException {
        vm.switchFlyingSystems();
        Path path = Paths.get("./scripts/autopilot.txt");
        String read = String.join("\n", Files.readAllLines(path));
        autopilotScript.setText(read);
        vm.runAutopilotScript();
    }

    public void loadAutopilotScript(ActionEvent actionEvent) throws IOException {
        Path path = openFilePath("Select autopilot script", "./scripts", "Txt files", "*.txt");
        if (path == null) return;
        String read = String.join("\n", Files.readAllLines(path));
        autopilotScript.setText(read);
        vm.runAutopilotScript();
    }

    public void connectToFlightGear(ActionEvent actionEvent) {
        openPopupScene();
        PopupSceneController.CONNECT_TO_FLIGHT_GEAR = true;
        PopupSceneController.CONNECT_TO_PATH_FINDER = false;
    }

    public void connectToPathfinder(ActionEvent actionEvent) {
        openPopupScene();
        PopupSceneController.CONNECT_TO_PATH_FINDER = true;
        PopupSceneController.CONNECT_TO_FLIGHT_GEAR = false;
    }

    public void openPopupScene() {
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("PopupScene.fxml"));
            Parent root = fxmlLoader.load();
            Stage popupWindowStage = new Stage();
            popupWindowStage.setTitle("Connect");
            popupWindowStage.setScene(new Scene(root));

            PopupSceneController psc = fxmlLoader.getController();
            psc.setViewModel(vm);

            if (!popupWindowStage.isShowing()) {
                popupWindowStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchFlyingSystems(ActionEvent mouseEvent) {
        vm.switchFlyingSystems();
    }
}
