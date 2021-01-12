package model.interpreter.commands;

import model.interpreter.expressions.ShuntingYard;

public class PrintCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "print";

    @Override
    public double execute() {
        String curr;
        try {
            System.out.println(ShuntingYard.calc(strings.peek()));
            strings.next();
        } catch (Exception ignored) {
            while (strings.hasNext()) {
                curr = strings.next();
                System.out.print(curr + " ");
            }
            System.out.println();
        }
        return 1;
    }
}
