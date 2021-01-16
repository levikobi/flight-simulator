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
import java.util.Observable;
import java.util.Observer;

public class MainSceneController implements Observer {

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
        Path path = openDataFilePath();
        if (path == null) return;
        heightMapController.parseMapFile(path);
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
