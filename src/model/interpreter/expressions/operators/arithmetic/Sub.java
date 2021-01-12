package model.interpreter.expressions.operators.arithmetic;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class Sub extends BinaryExpression {
    public Sub(Expression left, Expression right) {
        super(left, right);
    }

    public Sub() { }

    @Override
    public double calculate() {
        return left.calculate() - right.calculate();
    }
}
