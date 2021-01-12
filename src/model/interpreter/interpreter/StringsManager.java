package model.interpreter.interpreter;

public class StringsManager {
    private static class StringsHolder {
        public static final StringsIterator strings = new StringsIterator();
    }

    public static StringsIterator getStrings() {
        return StringsHolder.strings;
    }
}
