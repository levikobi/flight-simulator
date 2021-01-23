package model;

import model.interpreter.Interpreter;
import utils.Navigation;

public final class FlightGear {

    private final Interpreter interpreter;

    private double lat;
    private double lon;
    private double scale;

    public FlightGear() {
        this.interpreter = Interpreter.getInstance();
    }

    public void connect(String ip, int port) {
        String[] connectCommand = new String[] {
                "openDataServer 5400 10",
                "connect " + ip + " " + port,
        };
        interpreter.interpret(connectCommand);
    }

    public void setMapCoordinatesAndScale(double[] coordinates, double scale) {
        lon = coordinates[0];
        lat = coordinates[1];
        this.scale = scale * 1000;
    }

    public int[] getAirplanePosition() {
        double lon1 = interpreter.getProperty("airplane_lon");
        double lat1 = interpreter.getProperty("airplane_lat");
        double distance = Navigation.distance(lat, lat1, 0, lon, lon1, 0);
        double angle = 180 - Navigation.bearing(lat, lon, lat1, lon1);
        int x = (int) (distance * Math.sin(Math.toRadians(angle)) / scale);
        int y = (int) (distance * Math.cos(Math.toRadians(angle)) / scale);
        return new int[] {y, x};
    }

    public void runAutopilotScript(String[] script) {
        interpreter.interpret(script);
    }

    public void switchFlyingSystems() {
        interpreter.stop();
    }

    public void setProperty(String property, double value) {
        interpreter.interpret(new String[]{property + " = " + value});
    }

    public double getProperty(String property) {
        if (property.equals("rudder")) {
            return interpreter.getProperty("\"/controls/flight/rudder\"");
        } else if (property.equals("throttle")) {
            return interpreter.getProperty("\"/controls/engines/current-engine/throttle\"");
        } else if (property.equals("elevator")) {
            return interpreter.getProperty("\"/controls/flight/elevator\"");
        } else if (property.equals("aileron")) {
            return interpreter.getProperty("\"/controls/flight/aileron\"");
        }
        return 0;
    }
}
