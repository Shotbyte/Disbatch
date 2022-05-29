package com.github.commandant.command.parameter;

import java.util.Iterator;

class ParameterArgumentation implements ArgumentSelection, ArgumentQueue {
    private final String[] args;
    private int index;

    ParameterArgumentation(final String[] args) {
        this.args = args;
    }

    @Override
    public String firstArgument() {
        ensureWithinBounds();
        return args[index];
    }

    @Override
    public String nextArgument() {
        ensureWithinBounds();
        return args[index++];
    }

    private void ensureWithinBounds() {
        if (index == args.length)
            throw new IndexOutOfBoundsException("Max index bound reached");
    }

    @Override
    public Iterator<String> iterator() {
        return new SelectionIterator();
    }

    private class SelectionIterator implements Iterator<String> {
        private int index;

        @Override
        public boolean hasNext() {
            return index != args.length;
        }

        @Override
        public String next() {
            return args[index++];
        }
    }
}
