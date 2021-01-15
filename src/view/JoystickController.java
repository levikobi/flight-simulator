package view;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import viewmodel.ViewModel;

public class JoystickController {

    private ViewModel vm;

    @FXML public Circle thumbStick;
    @FXML public Slider rudder;
    @FXML public Slider throttle;

    public DoubleProperty aileron;
    public DoubleProperty elevator;

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
        rudder.valueProperty().bindBidirectional(vm.rudder);
        throttle.valueProperty().bindBidirectional(vm.throttle);
    }



    public void handlePressThumbStick(MouseEvent mouseEvent) {
        System.out.println("Pressed");
    }

    public void handleDragThumbStick(MouseEvent mouseEvent) {
        thumbStick.setCenterX(mouseEvent.getX());
        thumbStick.setCenterY(mouseEvent.getY());
    }

    public void handleMouseReleased(MouseEvent mouseEvent) {
        System.out.println("Released");
        thumbStick.setCenterX(0);
        thumbStick.setCenterY(0);
    }

    public void setRudder(MouseEvent mouseEvent) {
        vm.setRudder();
    }

    public void setThrottle(MouseEvent mouseEvent) {
        vm.setThrottle();
    }
}
