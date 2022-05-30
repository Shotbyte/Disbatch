package com.github.commandant.command.parameter.model;

public class StringParameter extends SenderIndependentParameter<String> {
    public StringParameter(final String label) {
        super(label);
    }

    @Override
    protected String parse(final String[] args) {
        return args[0];
    }

    @Override
    public boolean canParse(final String[] args) {
        return !args[0].isEmpty();
    }

    @Override
    public int getSize() {
        return 1;
    }
}
