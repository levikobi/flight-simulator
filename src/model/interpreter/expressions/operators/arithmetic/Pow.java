package model.interpreter.expressions.operators.arithmetic;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class Pow extends BinaryExpression {
    public Pow(Expression left, Expression right) {
        super(left, right);
    }

    public Pow() { }

    @Override
    public double calculate() {
        return Math.pow(left.calculate(), right.calculate());
    }
}
