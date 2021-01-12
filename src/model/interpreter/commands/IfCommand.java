package model.interpreter.commands;

import model.interpreter.expressions.Expression;

public class IfCommand extends ConditionParser {
    public static final String DISPLAY_NAME = "if";

    @Override
    public double execute() {
        initializeCondition();
        if (condition) {
            updateStrings();
            expressionsHolder.forEach(Expression::calculate);
        }
        expressionsHolder.clear();
        stringsHolder.clear();
        return 0;
    }
}
