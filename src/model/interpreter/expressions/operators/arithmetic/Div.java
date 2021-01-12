package model.interpreter.expressions.operators.arithmetic;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class Div extends BinaryExpression {
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    public Div() { }

    @Override
    public double calculate() {
        return left.calculate() / right.calculate();
    }
}