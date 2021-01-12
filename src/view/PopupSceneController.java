package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import viewmodel.ViewModel;

import java.util.Observable;
import java.util.Observer;

public class PopupSceneController implements Observer {

    public ViewModel vm;

    @FXML public TextField port;
    @FXML public TextField ip;

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
        vm.ip.bind(ip.textProperty());
        vm.port.bind(port.textProperty());
    }

    public void submitConnect(ActionEvent actionEvent) {
        vm.connectToFlightGear();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o != vm) return;
        if (arg.equals("Connected")) {
            Stage stage = (Stage) ip.getScene().getWindow();
            if (stage.isShowing()) stage.close();
        }
    }
}
