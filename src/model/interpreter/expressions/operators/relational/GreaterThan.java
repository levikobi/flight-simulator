package model.interpreter.expressions.operators.relational;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class GreaterThan extends BinaryExpression {
    public GreaterThan(Expression left, Expression right) {
        super(left, right);
    }

    public GreaterThan() { }

    @Override
    public double calculate() {
        return left.calculate() > right.calculate() ? 1 : 0;
    }
}