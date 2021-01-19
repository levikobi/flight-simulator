package model;

import model.interpreter.interpreter.Interpreter;
import model.interpreter.server.VariablesManager;
import utils.Navigation;

import java.util.List;
import java.util.Observable;

public class FlightSimulatorModel extends Observable implements Model {

    private Interpreter interpreter;
    private Pathfinder pathfinder;
    private double lat;
    private double lon;
    private double scale;

    public FlightSimulatorModel() {
        interpreter = Interpreter.getInstance();
        pathfinder = new Pathfinder();
    }

    @Override
    public void connectToFlightGear(String ip, int port) {
        String[] connectCommand = new String[]{
                "openDataServer 5400 10",
                "connect " + ip + " " + port,
                "var breaks = bind \"/controls/flight/speedbrake\"",
                "var throttle = bind \"/controls/engines/current-engine/throttle\"",
                "var heading = bind \"/instrumentation/heading-indicator/offset-deg\"",
                "var airspeed = bind \"/instrumentation/airspeed-indicator/indicated-speed-kt\"",
                "var roll = bind \"/instrumentation/attitude-indicator/indicated-roll-deg\"",
                "var pitch = bind \"/instrumentation/attitude-indicator/internal-pitch-deg\"",
                "var rudder = bind \"/controls/flight/rudder\"",
                "var aileron = bind \"/controls/flight/aileron\"",
                "var elevator = bind \"/controls/flight/elevator\"",
                "var alt = bind \"/instrumentation/altimeter/indicated-altitude-ft\"",
                "breaks = 0",
                "throttle = 1"
        };
        interpreter.interpret(connectCommand);
    }

    @Override
    public void connectToPathfinder(String ip, int port) {
        pathfinder.connect(ip, port);
        setChanged();
        notifyObservers(Message.CONNECT_PATHFINDER_SUCCESS);
    }

    @Override
    public void setMapCoordinatesAndScale(double[] coordinates, double scale) {
        lon = coordinates[0];
        lat = coordinates[1];
        this.scale = scale * 1000;
    }

    @Override
    public int[] getAirplanePosition() {
        double lon1 = VariablesManager.map.get("airplane_lon").value;
        double lat1 = VariablesManager.map.get("airplane_lat").value;
        double distance = Navigation.distance(lat, lat1, 0, lon, lon1, 0);
        double angle = 180 - Navigation.bearing(lat, lon, lat1, lon1);
        int x = (int) (distance * Math.sin(Math.toRadians(angle)) / scale);
        int y = (int) (distance * Math.cos(Math.toRadians(angle)) / scale);
//        System.out.println("xy: " + x + " , " + y);
        return new int[] {x, y};
    }

    @Override
    public void runAutopilotScript(String[] script) {
        interpreter.interpret(script);
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
        interpreter.stop();
    }

    @Override
    public void setProperty(String property, double value) {
        interpreter.interpret(new String[]{property + " = " + value});
    }
}
