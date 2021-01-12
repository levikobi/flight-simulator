package model;

public interface Model {
    void connect(String ip, int port);
    void runAutopilotScript(String[] script);
}
