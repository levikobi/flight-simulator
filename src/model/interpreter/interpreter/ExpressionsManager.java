package model.interpreter.interpreter;

/**
* Singleton Design Pattern...
* ExpressionsHolder makes it a lazy implementation instead of eager!
*/

public class ExpressionsManager {
    private static class ExpressionsHolder {
        public static final ExpressionsIterator expressions = new ExpressionsIterator();
    }

    public static ExpressionsIterator getExpressions() {
        return ExpressionsHolder.expressions;
    }
}
