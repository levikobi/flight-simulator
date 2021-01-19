package model;

import java.util.List;

public interface Model {
    void connectToFlightGear(String ip, int port);
    void connectToPathfinder(String ip, int port);

    void setProperty(String property, double value);

    int[] getAirplanePosition();
    void setMapCoordinatesAndScale(double[] coordinates, double scale);

    void runAutopilotScript(String[] script);

    void calculatePath(List<String> grid, String start, String end);
    String getPath();

    enum Message {
        CONNECT_PATHFINDER_SUCCESS,
        CALCULATE_PATH_SUCCESS
    }
}
