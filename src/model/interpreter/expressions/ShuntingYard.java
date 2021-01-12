package model.interpreter.expressions;

import model.interpreter.commands.DefineVarCommand;
import model.interpreter.expressions.operands.Num;
import model.interpreter.expressions.operators.arithmetic.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShuntingYard {
    public static double calc(String infixExpression){
        Queue<String> numbersQueue = new LinkedList<String>();
        Stack<String> operatorsStack = new Stack<String>();
        Stack<Expression> stackExp = new Stack<Expression>();

        String[] tokens = infixExpression.split("(?<=[-+*/()])|(?=[-+*/()])");
        for (String token : tokens){
            if (DefineVarCommand.programVariables.containsKey(token)) {
                numbersQueue.add(String.valueOf(DefineVarCommand.programVariables.get(token).value));
            }
            if (isDouble(token)){
                numbersQueue.add(token);
            }
            else{
                switch(token) {
                    case ArithmeticOperators.DIV:
                    case ArithmeticOperators.MUL:
                    case "(":
                        operatorsStack.push(token);
                        break;
                    case ArithmeticOperators.ADD:
                    case ArithmeticOperators.SUB:
                        while (!operatorsStack.empty() && (!operatorsStack.peek().equals("("))){
                            numbersQueue.add(operatorsStack.pop());
                        }
                        operatorsStack.push(token);
                        break;
                    case ")":
                        while (!operatorsStack.peek().equals("(")){
                            numbersQueue.add(operatorsStack.pop());
                        }
                        operatorsStack.pop();
                        break;
                }
            }
        }
        while(!operatorsStack.isEmpty()){
            numbersQueue.add(operatorsStack.pop());
        }

        for(String str : numbersQueue) {
            if (isDouble(str)){
                stackExp.push(new Num(Double.parseDouble(str)));
            }
            else{
                Expression right = stackExp.pop();
                Expression left = stackExp.isEmpty() ? new Num(0) : stackExp.pop();

                switch(str) {
                    case ArithmeticOperators.DIV:
                        stackExp.push(new Div(left, right));
                        break;
                    case ArithmeticOperators.MUL:
                        stackExp.push(new Mul(left, right));
                        break;
                    case ArithmeticOperators.ADD:
                        stackExp.push(new Add(left, right));
                        break;
                    case ArithmeticOperators.SUB:
                        stackExp.push(new Sub(left, right));
                        break;
                }
            }
        }

        return Math.floor(((double) stackExp.pop().calculate() * 1000)) /1000;
    }

    private static boolean isDouble(String val){
        try {
            Double.parseDouble(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
