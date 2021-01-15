package view.components;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import viewmodel.ViewModel;

public class JoystickController {

    private static double RADIUS;

    private ViewModel vm;

    @FXML public Circle border;
    @FXML public Circle thumbStick;
    @FXML public Slider rudder;
    @FXML public Slider throttle;

    public DoubleProperty aileron;
    public DoubleProperty elevator;

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
        rudder.valueProperty().bindBidirectional(vm.rudder);
        throttle.valueProperty().bindBidirectional(vm.throttle);
        RADIUS = border.radiusProperty().doubleValue();
        System.out.println("RADIUS: " + RADIUS);
    }

    public void handlePressThumbStick(MouseEvent mouseEvent) {
        System.out.println("Pressed");
    }

    public void handleDragThumbStick(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (2 * r > RADIUS) {
            double deg = Math.abs(Math.toDegrees(y >= 0 ?
                    Math.atan2(y, x) : Math.atan2(y * (-1), x * (-1))));
            y = (RADIUS / 2) * Math.sin(Math.toRadians(deg)) * ( y >= 0 ? 1 : -1);
            x = (RADIUS / 2) * Math.cos(Math.toRadians(deg)) * ( y >= 0 ? 1 : -1);
        }
        thumbStick.setCenterX(x);
        thumbStick.setCenterY(y);
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
