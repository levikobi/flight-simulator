package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewmodel.ViewModel;

import java.util.Observable;
import java.util.Observer;

public class PopupSceneController implements Observer {

    private static final String DEFAULT_IP = "127.0.0.1";

    public ViewModel vm;

    @FXML public TextField port;
    @FXML public TextField ip;

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
        vm.ip.bind(ip.textProperty());
        vm.port.bind(port.textProperty());
    }

    public void submitConnect(ActionEvent actionEvent) {
        if (ip.getText().isEmpty()) ip.setText(DEFAULT_IP);
        Stage popupWindow = (Stage) ip.getScene().getWindow();
        popupWindow.close();
        vm.connectToFlightGear();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o != vm) return;
//        if (arg.equals("Connected")) {
//            Stage stage = (Stage) ip.getScene().getWindow();
//            if (stage.isShowing()) stage.close();
//        }
    }
}
