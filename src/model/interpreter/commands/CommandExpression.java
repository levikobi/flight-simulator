package model.interpreter.commands;

import model.interpreter.expressions.Expression;

public class CommandExpression implements Expression {
    private Command command;

    public CommandExpression(Command command) {
        this.command = command;
    }

    @Override
    public double calculate() {
        return command.execute();
    }
}
