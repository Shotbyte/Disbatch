package com.github.commandant.command.parameter.model;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Retrieves an online {@link Player} by their name based on a parsable, passed argument.
 *
 * @param <T> {@inheritDoc}
 */
public final class PlayerByNameParameter<T extends CommandSender> extends AbstractParameter<T, Player> {
    public PlayerByNameParameter(final String usageLabel) {
        super(usageLabel);
    }

    @Override
    public boolean canParse(final String[] boundedArgs) {
        return Bukkit.getPlayer(boundedArgs[0]) != null;
    }

    @Override
    public Player parse(final String[] boundedArgs, final T sender) {
        return Bukkit.getPlayer(boundedArgs[0]);
    }
}
