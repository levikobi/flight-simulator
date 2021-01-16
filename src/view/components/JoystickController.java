package view.components;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import viewmodel.ViewModel;

public class JoystickController {

    private static double BORDER_RADIUS;
    private static double THUMBSTICK_RADIUS;

    private ViewModel vm;

    @FXML public Circle border;
    @FXML public Circle thumbStick;
    @FXML public Slider rudder;
    @FXML public Slider throttle;

    public DoubleProperty aileron = new SimpleDoubleProperty();
    public DoubleProperty elevator = new SimpleDoubleProperty();

    public void setViewModel(ViewModel vm) {
        this.vm = vm;

        vm.aileron.bind(aileron);
        vm.elevator.bind(elevator);

        rudder.valueProperty().bindBidirectional(vm.rudder);
        throttle.valueProperty().bindBidirectional(vm.throttle);

        BORDER_RADIUS = border.radiusProperty().doubleValue();
        THUMBSTICK_RADIUS = thumbStick.radiusProperty().doubleValue();
    }

    public void handleDragThumbStick(MouseEvent mouseEvent) {
        double x = mouseEvent.getX(), y = mouseEvent.getY();
        double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (2 * r > BORDER_RADIUS) {
            double deg = Math.abs(Math.toDegrees(y >= 0 ?
                    Math.atan2(y, x) : Math.atan2(y * (-1), x * (-1))));
            y = THUMBSTICK_RADIUS * Math.sin(Math.toRadians(deg)) * ( y >= 0 ? 1 : -1);
            x = THUMBSTICK_RADIUS * Math.cos(Math.toRadians(deg)) * ( y >= 0 ? 1 : -1);
        }
        thumbStick.setCenterX(x);
        thumbStick.setCenterY(y);
        aileron.set(x / THUMBSTICK_RADIUS);
        elevator.set(y / THUMBSTICK_RADIUS);
        vm.setAileronAndElevator();
    }

    public void handleMouseReleased(MouseEvent mouseEvent) {
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
