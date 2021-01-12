package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainSceneController {

    @FXML public TextArea textarea;

    public void loadDataFile(ActionEvent actionEvent) throws IOException {
        Path path = openDataFilePath();
        if (path == null) return;
        List<String> data = Files.readAllLines(path);
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

}
