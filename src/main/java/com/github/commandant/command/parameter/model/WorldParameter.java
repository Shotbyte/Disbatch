package com.github.commandant.command.parameter.model;

import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 *
 */
public class WorldParameter extends SenderIndependentParameter<World> {
    public WorldParameter(final String label) {
        super(label);
    }

    @Override
    public boolean canParse(final String[] args) {
        return getWorld(args[0]) != null;
    }

    @Override
    protected World parse(final String[] args) {
        return getWorld(args[0]);
    }

    private World getWorld(final String argument) {
        return Bukkit.getWorld(argument);
    }
}
