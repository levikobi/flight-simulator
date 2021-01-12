package model.interpreter.commands;

import model.interpreter.expressions.BinaryExpression;
import model.interpreter.expressions.Expression;
import model.interpreter.expressions.ShuntingYard;
import model.interpreter.expressions.operands.Num;
import model.interpreter.expressions.operators.relational.RelationalOperators;

import java.util.ArrayList;
import java.util.List;

public abstract class ConditionParser extends AbstractCommand {
    protected List<Expression> expressionsHolder;
    protected List<String> stringsHolder;
    protected boolean condition;

    private String leftOperand;
    private String rightOperand;
    private BinaryExpression relationalOperator;

    public ConditionParser() {
        this.expressionsHolder = new ArrayList<>();
        this.stringsHolder = new ArrayList<>();
    }

    protected void initializeCondition() {
        parseCondition();
        validateCondition();
        updateExpressionsHolder();
        updateStringsHolder();
    }

    private void parseCondition() {
        leftOperand = strings.next();
        relationalOperator = RelationalOperators.map.get(strings.next());
        rightOperand = strings.next();
        strings.next(); // for now, I ignore '{'
    }

    protected void validateCondition() {
        relationalOperator.setOperands(new Num(ShuntingYard.calc(leftOperand)),
                                       new Num(ShuntingYard.calc(rightOperand)));
        condition = relationalOperator.calculate() == 1;
    }

    private void updateExpressionsHolder() {
        while (expressions.hasNext()) {
            expressionsHolder.add(expressions.next());
        }
    }

    private void updateStringsHolder() {
        while (strings.hasNext()) {
            if (strings.peek().equals("}")) strings.next();
            else stringsHolder.add(strings.next());
        }
    }

    protected void updateExpressions() {
        for (Expression expression : expressionsHolder) {
            expressions.push(expression);
        }
    }

    protected void updateStrings() {
        for (String string : stringsHolder) {
            strings.push(string);
        }
    }
}
