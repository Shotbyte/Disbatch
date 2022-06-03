package com.github.commandant.command.parameter.model;

/**
 *
 */
public class StringParameter extends SenderIndependentParameter<String> {
    public StringParameter(final String label) {
        super(label);
    }

    @Override
    public boolean canParse(final String[] args) {
        for (final String arg : args)
            if (arg.isEmpty()) return false;

        return true;
    }

    @Override
    protected String parse(final String[] args) {
        return String.join(" ", args);
    }

    @Override
    public int getSize() {
        return Integer.MAX_VALUE;
    }
}
