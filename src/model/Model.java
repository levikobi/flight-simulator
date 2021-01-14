package model;

public interface Model {
    void connect(String ip, int port);
    void setMapCoordinatesAndScale(double[] coordinates, double scale);
    int[] getAirplanePosition();
    void runAutopilotScript(String[] script);
}
