package model.interpreter.interpreter;

import model.interpreter.expressions.Expression;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ExpressionsIterator {
    private ConcurrentLinkedQueue<Expression> expressions;

    public ExpressionsIterator() {
        this.expressions = new ConcurrentLinkedQueue<>();
    }

    public void push(Expression expression) {
        expressions.offer(expression);
    }

    public Expression peek() {
        return expressions.peek();
    }

    public Expression next() {
        return expressions.poll();
    }

    public boolean hasNext() {
        return !expressions.isEmpty();
    }
}
