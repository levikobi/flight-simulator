package model.interpreter.commands;

import model.interpreter.interpreter.*;

public abstract class AbstractCommand implements Command {
    protected ExpressionsIterator expressions;
    protected StringsIterator strings;


    public AbstractCommand() {
        this.expressions = ExpressionsManager.getExpressions();
        this.strings = StringsManager.getStrings();
    }
}
