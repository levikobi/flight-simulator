package model.interpreter.server;

public class VariableBinder {
    public double value;
    public final String simulatorPath;

    public VariableBinder(double value, String simulatorPath) {
        this.value = value;
        this.simulatorPath = simulatorPath;
    }
}
