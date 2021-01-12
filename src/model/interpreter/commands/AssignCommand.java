package model.interpreter.commands;

import model.interpreter.expressions.ShuntingYard;
import model.interpreter.server.VariableBinder;

public class AssignCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "=";

    @Override
    public double execute() {
        if (expressions.hasNext()) { // BindCommand
            expressions.next().calculate();
        } else {
            String variableName = strings.next();
            VariableBinder binder = DefineVarCommand.programVariables.get(variableName);
            binder.value = ShuntingYard.calc(strings.next());
            if (!DefineVarCommand.programVariables.get(variableName).simulatorPath.equals("")) {
                ConnectCommand.out.println("set " + binder.simulatorPath + " " + binder.value);
            }
        }
        return 0;
    }
}
