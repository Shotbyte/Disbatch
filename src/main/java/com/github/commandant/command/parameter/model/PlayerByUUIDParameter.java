package com.github.commandant.command.parameter.model;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Retrieves an online {@link Player} by their {@link UUID} based on a parsable, passed argument.
 *
 * @param <T> {@inheritDoc}
 */
public final class PlayerByUUIDParameter<T extends CommandSender> extends UUIDOrientedParameter<T, Player> {
    public PlayerByUUIDParameter(final String usageLabel) {
        super(usageLabel);
    }

    @Override
    public boolean canParse(final String[] args) {
        return super.canParse(args) && Bukkit.getPlayer(UUID.fromString(args[0])) != null;
    }

    @Override
    public Player parse(final String[] boundedArgs, final T sender) {
        return Bukkit.getPlayer(UUID.fromString(boundedArgs[0]));
    }
}
