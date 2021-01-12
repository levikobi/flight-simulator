package model.interpreter.expressions.operators.relational;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class GreaterThanOrEqual extends BinaryExpression {
    public GreaterThanOrEqual(Expression left, Expression right) {
        super(left, right);
    }

    public GreaterThanOrEqual() { }

    @Override
    public double calculate() {
        return left.calculate() >= right.calculate() ? 1 : 0;
    }
}
