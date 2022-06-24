package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * Holds the functionalities necessary to create or retrieve a {@link UUID} based on a parsable, passed argument.
 *
 * @param <K> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public abstract class UUIDOrientedParameter<K extends CommandSender, V> extends AbstractParameter<K, V> {
    private static final String UUID_REGEX = "[\\da-fA-F]{8}-[\\da-fA-F]{4}-[34][\\da-fA-F]{3}-[89ab][\\da-fA-F]{3}-[\\da-fA-F]{12}";

    public UUIDOrientedParameter(final String usageLabel) {
        super(usageLabel);
    }

    @Override
    public boolean canParse(final String[] args) {
        return UUID_REGEX.matches(args[0]);
    }
}
