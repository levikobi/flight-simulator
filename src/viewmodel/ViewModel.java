package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ViewModel extends Observable implements Observer {

    private Model model;

    public StringProperty ip;
    public StringProperty port;
    public StringProperty airplanePosition;
    public StringProperty autopilotScript;

    public DoubleProperty rudder;
    public DoubleProperty throttle;

    public ViewModel(Model model) {
        this.model = model;

        ip = new SimpleStringProperty();
        port = new SimpleStringProperty();
        airplanePosition = new SimpleStringProperty();
        autopilotScript = new SimpleStringProperty();

        rudder = new SimpleDoubleProperty();
        throttle = new SimpleDoubleProperty();
    }

    public void connectToFlightGear() {
        model.connect(ip.get(), Integer.parseInt(port.get()));
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::updateAirplanePosition, 0, 250, TimeUnit.MILLISECONDS);
        setChanged();
        notifyObservers("Connected");
    }

    public void runAutopilotScript() {
        model.runAutopilotScript(autopilotScript.get().split("\n"));
    }

    private void updateAirplanePosition() {
        CompletableFuture
                .supplyAsync(() -> model.getAirplanePosition())
                .thenApply(position -> position[0] + "," + position[1])
                .thenAccept(position -> airplanePosition.set(position));
    }

    public void setMapCoordinatesAndScale(List<String> data) {
        double[] coordinates = Arrays
                .stream(data.get(0).split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();
        double scale = Double.parseDouble(data.get(1).split(",")[0]);
        model.setMapCoordinatesAndScale(coordinates, scale);
    }

    public void setRudder() {
        model.setProperty("rudder", rudder.get());
    }

    public void setThrottle() {
        model.setProperty("throttle", throttle.get());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
