package model.interpreter.expressions;

public abstract class BinaryExpression implements Expression {
    protected Expression left;
    protected Expression right;

    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public BinaryExpression() {}

    public void setOperands(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
