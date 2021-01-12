package model.interpreter.expressions.operators.relational;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class Equal extends BinaryExpression {
    public Equal(Expression left, Expression right) {
        super(left, right);
    }

    public Equal() { }

    @Override
    public double calculate() {
        return left.calculate() == right.calculate() ? 1 : 0;
    }
}
