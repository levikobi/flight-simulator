package model.interpreter.interpreter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Interpreter {

    private final ExpressionsIterator expressions;
    private final StringsIterator strings;
    private ExecutorService executor;
    private boolean newBlockScope;
    public boolean isStopped;

    private static class InterpreterHolder {
        public static final Interpreter interpreter = new Interpreter();
    }

    public static Interpreter getInstance() {
        return InterpreterHolder.interpreter;
    }

    private Interpreter() {
        this.expressions = ExpressionsManager.getExpressions();
        this.strings = StringsManager.getStrings();
        executor = Executors.newSingleThreadExecutor();
    }

    public void interpret(String[] data) {
        isStopped = false;
        for (String line : data) {
            executor.submit(() -> lexer(line));
        }
    }

    public void stop() {
        executor.shutdownNow();
        executor = Executors.newSingleThreadExecutor();
        expressions.clear();
        strings.clear();
        newBlockScope = false;
        isStopped = true;
    }

    private double lexer(String line) {
        String[] lexemes = evaluating(line);
        for (String lexeme : lexemes) {
            if (ExpressionsFactory.containsKey(lexeme)) {
                expressions.push(ExpressionsFactory.get(lexeme));
            } else {
                strings.push(lexeme);
            }
        }
        return parser();
    }

    private String[] evaluating(String line) {
        return line.trim()
                .replace("=", " = ")
                .replace(" + ", "+")
                .replace(" / ", "/")
                .replace(" - ", "-")
                .replace(" * ", "*")
                .split("\\s+");
    }

    private double parser() {
        if (!expressions.hasNext()) return 0;
        if (strings.hasNext() && strings.peekLast().equals("{")) {
            newBlockScope = true;
        }
        if (strings.hasNext() && strings.peekLast().equals("}")) {
            newBlockScope = false;
        }
        if (!newBlockScope) return expressions.next().calculate();
        return 0;
    }
}
