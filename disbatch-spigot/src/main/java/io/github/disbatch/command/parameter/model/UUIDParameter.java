package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.builder.Suggester;
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
    public UUID parse(final CommandInput input, final T sender) {
        return UUID.fromString(input.getArgument(0));
    }
}
