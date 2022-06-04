package com.github.commandant.command.parameter.model;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Retrieves an online {@link Player} based on a parsable, passed argument.
 */
public class PlayerParameter extends SenderIndependentParameter<Player> {
    public PlayerParameter(final String usageLabel) {
        super(usageLabel);
    }

    @Override
    public boolean canParse(final String[] args) {
        return getPlayer(args[0]) != null;
    }

    @Override
    protected Player parse(final String[] args) {
        return getPlayer(args[0]);
    }

    private Player getPlayer(final String argument) {
        return Bukkit.getPlayer(argument);
    }
}
