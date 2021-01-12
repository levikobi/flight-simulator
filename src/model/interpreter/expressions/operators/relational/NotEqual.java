package model.interpreter.expressions.operators.relational;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class NotEqual extends BinaryExpression {
    public NotEqual(Expression left, Expression right) {
        super(left, right);
    }

    public NotEqual() { }

    @Override
    public double calculate() {
        return left.calculate() != right.calculate() ? 1 : 0;
    }
}
