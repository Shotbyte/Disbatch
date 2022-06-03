package com.github.commandant.command.parameter.model;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 */
public class PlayerParameter extends SenderIndependentParameter<Player> {
    public PlayerParameter(final String label) {
        super(label);
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
