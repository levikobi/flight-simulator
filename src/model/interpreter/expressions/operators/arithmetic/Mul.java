package model.interpreter.expressions.operators.arithmetic;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class Mul extends BinaryExpression {
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    public Mul() { }

    @Override
    public double calculate() {
        return left.calculate() * right.calculate();
    }
}
