package model.interpreter.interpreter;

import model.interpreter.commands.*;
import model.interpreter.expressions.Expression;

import java.util.HashMap;
import java.util.Map;

public class ExpressionsFactory {
    private static final Map<String, Expression> expressionsPool = new HashMap<String, Expression>(){{
        put(OpenServerCommand.DISPLAY_NAME, new CommandExpression(new OpenServerCommand()));
        put(ConnectCommand.DISPLAY_NAME, new CommandExpression(new ConnectCommand()));
        put(DefineVarCommand.DISPLAY_NAME, new CommandExpression(new DefineVarCommand()));
        put(AssignCommand.DISPLAY_NAME, new CommandExpression(new AssignCommand()));
        put(BindCommand.DISPLAY_NAME, new CommandExpression(new BindCommand()));
        put(WhileCommand.DISPLAY_NAME, new CommandExpression(new WhileCommand()));
        put(IfCommand.DISPLAY_NAME, new CommandExpression(new IfCommand()));
        put(PrintCommand.DISPLAY_NAME, new CommandExpression(new PrintCommand()));
        put(ReturnCommand.DISPLAY_NAME, new CommandExpression(new ReturnCommand()));
        put(DisconnectCommand.DISPLAY_NAME, new CommandExpression(new DisconnectCommand()));
        put(SleepCommand.DISPLAY_NAME, new CommandExpression(new SleepCommand()));
    }};

    public static boolean containsKey(String expressionName) {
        return expressionsPool.containsKey(expressionName);
    }

    public static Expression get(String expressionName) {
        return expressionsPool.get(expressionName);
    }
}
