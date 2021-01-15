package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FlightSimulatorModel;
import viewmodel.ViewModel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlightSimulatorModel model = new FlightSimulatorModel();
        ViewModel vm = new ViewModel(model);
        model.addObserver(vm);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        Parent root = (Parent) loader.load();

//        URL resource = getClass().getResource("MainScene.fxml");
//        HBox hBox = fxl.load(resource.openStream());

//        MainSceneController msc = fxl.getController();
        MainSceneController msc = loader.getController();
        msc.setViewModel(vm);
        vm.addObserver(msc);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
