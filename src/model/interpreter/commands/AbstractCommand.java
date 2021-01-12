package model.interpreter.commands;

import model.interpreter.interpreter.ExpressionsIterator;
import model.interpreter.interpreter.ExpressionsManager;
import model.interpreter.interpreter.StringsIterator;
import model.interpreter.interpreter.StringsManager;

public abstract class AbstractCommand implements Command {
    protected ExpressionsIterator expressions;
    protected StringsIterator strings;


    public AbstractCommand() {
        this.expressions = ExpressionsManager.getExpressions();
        this.strings = StringsManager.getStrings();
    }
}
