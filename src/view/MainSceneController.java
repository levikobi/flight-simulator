package view;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainSceneController {

    public TextArea textarea;

    public void loadAutopilotScript(ActionEvent actionEvent) throws IOException {
        Path path = Paths.get("scripts/autopilot.txt");
        String read = String.join("\n", Files.readAllLines(path));
        textarea.setText(read);
    }

}
