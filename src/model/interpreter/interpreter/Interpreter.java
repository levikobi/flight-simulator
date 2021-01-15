package model.interpreter.interpreter;

public class Interpreter {
    private final ExpressionsIterator expressions;
    private final StringsIterator strings;
    private boolean newBlockScope;

    public Interpreter() {
        this.expressions = ExpressionsManager.getExpressions();
        this.strings = StringsManager.getStrings();
    }

    public void interpret(String[] data) {
        new Thread(() -> {
            for (String line : data) {
                lexer(line);
            }
        }).start();
    }

    public void interpret(String data) {
        new Thread(() -> {
            lexer(data);
        }).start();
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
