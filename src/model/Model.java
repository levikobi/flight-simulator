package model;

public interface Model {
    void connect(String ip, int port);
    double[] getAirplanePosition();
    void runAutopilotScript(String[] script);
}
