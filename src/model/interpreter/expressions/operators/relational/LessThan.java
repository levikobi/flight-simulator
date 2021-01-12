package model.interpreter.expressions.operators.relational;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class LessThan extends BinaryExpression {
    public LessThan(Expression left, Expression right) {
        super(left, right);
    }

    public LessThan() {
    }

    @Override
    public double calculate() {
        return left.calculate() < right.calculate() ? 1 : 0;
    }
}