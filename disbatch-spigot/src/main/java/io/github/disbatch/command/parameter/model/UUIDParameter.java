package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;

import java.util.Optional;
import java.util.UUID;

/**
 * Creates a {@link UUID} based on a parsable, passed argument.
 */
public final class UUIDParameter<S extends CommandSender> extends UUIDOrientedParameter<S, UUID> {

    /**
     * @param uuidLabel
     */
    public UUIDParameter(final String uuidLabel) {
        super(uuidLabel);
    }

    @Override
    public Optional<UUID> parse(final CommandInput input, final S sender) {
        final String arg = input.getArgument(0);
        return isUniqueId(arg) ? Optional.of(UUID.fromString(arg)) : Optional.empty();
    }
}
