package model.interpreter.expressions.operators.arithmetic;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;

public class Add extends BinaryExpression {
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    public Add() { }

    @Override
    public double calculate() {
        return left.calculate() + right.calculate();
    }
}
