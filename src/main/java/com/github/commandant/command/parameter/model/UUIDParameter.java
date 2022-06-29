package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * Creates a {@link UUID} based on a parsable, passed argument.
 */
public final class UUIDParameter<T extends CommandSender> extends UUIDOrientedParameter<T, UUID> {
    public UUIDParameter(final String usageLabel) {
        super(usageLabel);
    }

    public UUIDParameter(final String usageLabel, final Suggester<T> suggester) {
        super(usageLabel, suggester);
    }

    @Override
    public UUID parse(final String[] args, final T sender) {
        return UUID.fromString(args[0]);
    }
}
