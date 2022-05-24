package com.github.commandant.parameter.model;

import com.github.commandant.parameter.ArgumentQueue;
import com.github.commandant.parameter.ArgumentSelection;

public class StringParameter extends SenderIndependentParameter<String> {
    public StringParameter(final String label) {
        super(label);
    }

    @Override
    protected String parse(final ArgumentQueue queue) {
        return queue.nextArgument();
    }

    @Override
    public boolean canParse(final ArgumentSelection selection) {
        return !selection.firstArgument().isEmpty();
    }

    @Override
    public int getSize() {
        return 1;
    }
}
