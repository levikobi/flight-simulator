package model;

import model.interpreter.interpreter.Interpreter;

import java.util.Observable;

public class FlightSimulatorModel extends Observable implements Model {

    private Interpreter interpreter;

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
    public void runAutopilotScript(String[] script) {
        interpreter.interpret(script);
    }
}
