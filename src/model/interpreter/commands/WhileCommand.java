package model.interpreter.commands;

import model.interpreter.expressions.Expression;

public class WhileCommand extends ConditionParser {
    public static final String DISPLAY_NAME = "while";

    @Override
    public double execute() {
        initializeCondition();
        while (condition) {
            updateStrings();
            expressionsHolder.forEach(Expression::calculate);
            validateCondition();
        }
        expressionsHolder.clear();
        stringsHolder.clear();
        return 0;
    }
}