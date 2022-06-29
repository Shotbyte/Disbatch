package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

/**
 * Retrieves an online {@link Player} by their {@link UUID} based on a parsable, passed argument.
 *
 * @param <T> {@inheritDoc}
 */
public final class PlayerFromUUIDParameter<T extends CommandSender> extends UUIDOrientedParameter<T, Optional<Player>> {
    public PlayerFromUUIDParameter(final String uuidLabel) {
        super(uuidLabel);
    }

    public PlayerFromUUIDParameter(final String uuidLabel, final Suggester<T> suggester) {
        super(uuidLabel, suggester);
    }

    @Override
    public Optional<Player> parse(final String[] boundedArgs, final T sender) {
        return Optional.ofNullable(Bukkit.getPlayer(UUID.fromString(boundedArgs[0])));
    }
}
