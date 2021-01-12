package model.interpreter.commands;

import model.interpreter.expressions.ShuntingYard;

public class ReturnCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "return";

    @Override
    public double execute() {
        StringBuilder str = new StringBuilder();
        while (strings.hasNext()) {
            str.append(strings.next());
        }

        return (int) ShuntingYard.calc(str.toString());
    }
}