package model.interpreter.interpreter;

import java.util.ArrayDeque;

public class StringsIterator {
    private ArrayDeque<String> strings;

    public StringsIterator() {
        this.strings = new ArrayDeque<>();
    }

    public void push(String string) {
        strings.add(string);
    }

    public String peek() {
        return strings.peek();
    }

    public String peekLast() {
        return strings.peekLast();
    }

    public String next() {
        return strings.poll();
    }

    public boolean hasNext() {
        return !strings.isEmpty();
    }

    public void clear() { strings.clear(); }
}
