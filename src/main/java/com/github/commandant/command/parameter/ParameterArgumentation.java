package com.github.commandant.command.parameter;

import com.github.commandant.command.parameter.model.Parameter;

import java.util.Iterator;

class ParameterArgumentation implements ArgumentSelection, ArgumentQueue {
    private final String[] args;
    private int index;

    ParameterArgumentation(final String[] args, final Parameter<?, ?> parameter) {
        this.args = args;
        index = (args.length - parameter.getSize()) - 1;
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
        private int index = ParameterArgumentation.this.index - 1;

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
