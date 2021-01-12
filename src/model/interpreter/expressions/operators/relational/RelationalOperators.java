package model.interpreter.expressions.operators.relational;

import model.interpreter.expressions.BinaryExpression;

import java.util.HashMap;
import java.util.Map;

public class RelationalOperators {
    public static final String EQUAL = "==";
    public static final String NOT_EQUAL = "!=";
    public static final String GREATER_THAN = ">";
    public static final String LESS_THAN = "<";
    public static final String GREATER_THAN_OR_EQUAL = ">=";
    public static final String LESS_THAN_OR_EQUAL = "<=";

    public static final Map<String, BinaryExpression> map = new HashMap<String, BinaryExpression>(){{
        put(EQUAL, new Equal());
        put(NOT_EQUAL, new NotEqual());
        put(GREATER_THAN, new GreaterThan());
        put(LESS_THAN, new LessThan());
        put(GREATER_THAN_OR_EQUAL, new GreaterThanOrEqual());
        put(LESS_THAN_OR_EQUAL, new LessThanOrEqual());
    }};
}
