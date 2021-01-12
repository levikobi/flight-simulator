package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.FlightSimulatorModel;
import viewmodel.ViewModel;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlightSimulatorModel model = new FlightSimulatorModel();
        ViewModel vm = new ViewModel(model);
        model.addObserver(vm);

        FXMLLoader fxl = new FXMLLoader();
        URL resource = getClass().getResource("MainScene.fxml");
        HBox hBox = fxl.load(resource.openStream());

        MainSceneController msc = fxl.getController();
        msc.setViewModel(vm);
        vm.addObserver(msc);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(hBox));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
