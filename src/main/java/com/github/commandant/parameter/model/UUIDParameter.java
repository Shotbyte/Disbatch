package com.github.commandant.parameter.model;

import com.github.commandant.parameter.ArgumentQueue;
import com.github.commandant.parameter.ArgumentSelection;

import java.util.UUID;

/**
 *
 */
public class UUIDParameter extends SenderIndependentParameter<UUID> {
    private static final String UUID_REGEX = "[\\da-fA-F]{8}-[\\da-fA-F]{4}-[34][\\da-fA-F]{3}-[89ab][\\da-fA-F]{3}-[\\da-fA-F]{12}";

    public UUIDParameter(final String label) {
        super(label);
    }

    @Override
    public boolean canParse(final ArgumentSelection selection) {
        return UUID_REGEX.matches(selection.firstArgument());
    }

    @Override
    protected UUID parse(final ArgumentQueue queue) {
        return UUID.fromString(queue.nextArgument());
    }

    @Override
    public int getSize() {
        return 1;
    }
}
