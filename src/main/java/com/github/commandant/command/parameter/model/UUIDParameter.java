package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * Creates a {@link UUID} based on a parsable, passed argument.
 */
public final class UUIDParameter<T extends CommandSender> extends UUIDOrientedParameter<T, UUID> {
    public UUIDParameter(final String usageLabel) {
        super(usageLabel);
    }

    @Override
    public UUID parse(final String[] args, final T sender) {
        return UUID.fromString(args[0]);
    }
}
