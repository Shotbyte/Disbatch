package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Retrieves an online {@link Player} by their name based on a parsable, passed argument.
 *
 * @param <T> {@inheritDoc}
 */
public final class PlayerFromNameParameter<T extends CommandSender> extends StringParsableParameter<T, Optional<Player>> {
    public PlayerFromNameParameter(final String playerNameLabel) {
        super(playerNameLabel);
    }

    public PlayerFromNameParameter(final String playerNameLabel, final Suggester<T> suggester) {
        super(suggester, playerNameLabel);
    }

    @Override
    public Optional<Player> parse(final String[] boundedArgs, final T sender) {
        return Optional.ofNullable(Bukkit.getPlayer(boundedArgs[0]));
    }
}
