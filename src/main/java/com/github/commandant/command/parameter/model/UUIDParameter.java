package com.github.commandant.command.parameter.model;

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
    public boolean canParse(final String[] args) {
        return UUID_REGEX.matches(args[0]);
    }

    @Override
    protected UUID parse(final String[] args) {
        return UUID.fromString(args[0]);
    }
}
