package model;

import java.util.List;
import java.util.Observable;

public class FlightSimulatorModel extends Observable implements Model {

    private final FlightGear flightGear;
    private final Pathfinder pathfinder;

    public FlightSimulatorModel() {
        flightGear = new FlightGear();
        pathfinder = new Pathfinder();
    }

    @Override
    public void connectToFlightGear(String ip, int port) {
        flightGear.connect(ip, port);
    }

    @Override
    public void connectToPathfinder(String ip, int port) {
        pathfinder.connect(ip, port);
        setChanged();
        notifyObservers(Message.CONNECT_PATHFINDER_SUCCESS);
    }

    @Override
    public void setMapCoordinatesAndScale(double[] coordinates, double scale) {
        flightGear.setMapCoordinatesAndScale(coordinates, scale);
    }

    @Override
    public int[] getAirplanePosition() {
        return flightGear.getAirplanePosition();
    }

    @Override
    public void runAutopilotScript(String[] script) {
        flightGear.runAutopilotScript(script);
    }

    @Override
    public void calculatePath(List<String> grid, String start, String end) {
        pathfinder.calculateShortestPath(grid, start, end);
        setChanged();
        notifyObservers(Message.CALCULATE_PATH_SUCCESS);
    }

    @Override
    public String getPath() {
        return pathfinder.getShortestPath();
    }

    @Override
    public void switchFlyingSystems() {
        flightGear.switchFlyingSystems();
    }

    @Override
    public void setProperty(String property, double value) {
        flightGear.setProperty(property, value);
    }
}
