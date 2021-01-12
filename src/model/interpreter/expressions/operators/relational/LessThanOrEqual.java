package model.interpreter.expressions.operators.relational;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class LessThanOrEqual extends BinaryExpression {
    public LessThanOrEqual(Expression left, Expression right) {
        super(left, right);
    }

    public LessThanOrEqual() { }

    @Override
    public double calculate() {
        return left.calculate() <= right.calculate() ? 1 : 0;
    }
}
