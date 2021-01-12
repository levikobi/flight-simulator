package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewmodel.ViewModel;

public class PopupSceneController {

    public ViewModel vm;

    @FXML public TextField port;
    @FXML public TextField ip;

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
        vm.ip.bind(ip.textProperty());
        vm.port.bind(port.textProperty());
    }

}
