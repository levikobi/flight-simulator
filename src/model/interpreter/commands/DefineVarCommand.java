package model.interpreter.commands;

import model.interpreter.server.VariableBinder;

import java.util.HashMap;
import java.util.Map;

public class DefineVarCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "var";

    private static final String UNDEFINED_PATH = "";

    public static Map<String, VariableBinder> programVariables = new HashMap<>();

    @Override
    public double execute() {
        String variableName = strings.peek();
        programVariables.put(variableName, new VariableBinder(0d, UNDEFINED_PATH));
        if (expressions.hasNext()) { // AssignCommand
            expressions.next().calculate();
        } else {
            strings.next();
        }

        return 0;
    }
}
