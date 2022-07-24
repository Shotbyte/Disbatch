package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.builder.Suggester;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * Retrieves an {@link OfflinePlayer} based on a parsable, passed argument.
 *
 * @param <T> {@inheritDoc}
 */
public final class OfflinePlayerParameter<T extends CommandSender> extends UUIDOrientedParameter<T, OfflinePlayer> {
    public OfflinePlayerParameter(final String uuidLabel) {
        super(uuidLabel);
    }

    public OfflinePlayerParameter(final String uuidLabel, final Suggester<T> suggester) {
        super(uuidLabel, suggester);
    }

    @Override
    public OfflinePlayer parse(final CommandInput args, final T sender) {
        return Bukkit.getOfflinePlayer(UUID.fromString(args.getArgument(0)));
    }
}
