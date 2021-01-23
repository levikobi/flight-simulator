package model;

import java.io.IOException;
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
        setChanged();
        try {
            pathfinder.connect(ip, port);
            System.out.println("Connected to pathfinding server successfully.");
            notifyObservers(Message.CONNECT_PATHFINDER_SUCCESS);
        } catch (IOException e) { notifyObservers(Message.CONNECT_PATHFINDER_FAILURE); }
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
        new Thread(() -> {
            setChanged();
            try {
                pathfinder.calculateShortestPath(grid, start, end);
                notifyObservers(Message.CALCULATE_PATH_SUCCESS);
            } catch (Exception e) { notifyObservers(Message.CALCULATE_PATH_FAILURE); }
        }).start();
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

    @Override
    public double getProperty(String property) {
        return flightGear.getProperty(property);
    }
}
