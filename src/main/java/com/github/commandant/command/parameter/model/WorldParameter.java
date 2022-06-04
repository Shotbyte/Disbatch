package com.github.commandant.command.parameter.model;

import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 * Retrieves a {@link World} based on a parsable, passed argument.
 */
public class WorldParameter extends SenderIndependentParameter<World> {
    public WorldParameter(final String usageLabel) {
        super(usageLabel);
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
