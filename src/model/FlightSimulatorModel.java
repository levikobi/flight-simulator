package model;

import model.interpreter.interpreter.Interpreter;
import model.interpreter.server.VariablesManager;

import java.util.Observable;

public class FlightSimulatorModel extends Observable implements Model {

    private Interpreter interpreter;
    private double lat;
    private double lon;
    private double scale;

    public FlightSimulatorModel() {
        interpreter = new Interpreter();
    }

    @Override
    public void connect(String ip, int port) {
        String[] connectCommand = new String[]{
                "openDataServer 5400 10",
                "connect " + ip + " " + port
        };
        interpreter.interpret(connectCommand);
    }

    @Override
    public void setMapCoordinatesAndScale(double[] coordinates, double scale) {
        lon = coordinates[0];
        lat = coordinates[1];
        this.scale = scale;
    }

    @Override
    public int[] getAirplanePosition() {
        double lon1 = VariablesManager.map.get("airplane_lon").value;
        double lat1 = VariablesManager.map.get("airplane_lat").value;
        System.out.println("LatLon: " + lat1 + " , " + lon1);
        double distance = Geometry.distance(lat, lat1, lon, lon1, 0, 0);
        double angle = Geometry.calcDegreeLatLon(lat1, lon1);
        int x = (int) (distance * Math.sin(angle * Math.PI / 180) * scale);
        int y = (int) (distance * Math.cos(angle * Math.PI / 180) * scale);
        System.out.println("xy: " + x + " , " + y);
        return new int[] {x, y};
    }

    @Override
    public void runAutopilotScript(String[] script) {
        interpreter.interpret(script);
    }

    public static void main(String[] args) {
        System.out.println(Geometry.distance(19.72137967, 21.443738, -155.0578122, -158.020959, 6, 426));
//        System.out.println(20 * Math.sin(30 * Math.PI / 180));
//        System.out.println(Geometry.calcDegreeLatLon(-158.020959, 21.443738, -155.0578118, 19.72137997));
    }
}
