package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewmodel.ViewModel;

public class PopupSceneController {

    private static final String DEFAULT_IP = "127.0.0.1";

    public static boolean CONNECT_TO_FLIGHT_GEAR;
    public static boolean CONNECT_TO_PATH_FINDER;

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
        if (CONNECT_TO_FLIGHT_GEAR) vm.connectToFlightGear();
        if (CONNECT_TO_PATH_FINDER) vm.connectToPathfinder();
    }
}
