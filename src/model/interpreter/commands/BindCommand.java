package model.interpreter.commands;

import model.interpreter.server.VariablesManager;

public class BindCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "bind";

    @Override
    public double execute() {
        String variableName = strings.next();
        String variablePath = strings.next();
        DefineVarCommand.programVariables.put(variableName, VariablesManager.map.get(variablePath));
        return 0;
    }
}
