package model.interpreter.commands;

import model.interpreter.expressions.ShuntingYard;

import static java.lang.Thread.sleep;

public class SleepCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "sleep";

    @Override
    public double execute() {
        int sleepingTime = (int) ShuntingYard.calc(strings.next());
        try {
            sleep(sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
