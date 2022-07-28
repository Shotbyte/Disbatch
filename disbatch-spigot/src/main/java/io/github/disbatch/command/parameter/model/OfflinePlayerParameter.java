package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Optional;
import java.util.UUID;

/**
 * Retrieves an {@link OfflinePlayer} based on a parsable, passed argument.
 *
 * @param <S> {@inheritDoc}
 */
public final class OfflinePlayerParameter<S extends CommandSender> extends UUIDOrientedParameter<S, OfflinePlayer> {
    public OfflinePlayerParameter(final String uuidLabel) {
        super(uuidLabel);
    }

    @Override
    public Optional<OfflinePlayer> parse(final CommandInput args, final S sender) {
        return Optional.of(Bukkit.getOfflinePlayer(UUID.fromString(args.getArgument(0))));
    }
}
