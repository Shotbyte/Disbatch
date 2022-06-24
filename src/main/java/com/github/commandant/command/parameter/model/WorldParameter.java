package com.github.commandant.command.parameter.model;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

/**
 * Retrieves a {@link World} based on a parsable, passed argument.
 */
public final class WorldParameter<T extends CommandSender> extends AbstractParameter<T, World> {
    public WorldParameter(final String usageLabel) {
        super(usageLabel);
    }

    @Override
    public boolean canParse(final String[] args) {
        return getWorld(args[0]) != null;
    }

    @Override
    public World parse(final String[] args, final T sender) {
        return getWorld(args[0]);
    }

    private World getWorld(final String argument) {
        return Bukkit.getWorld(argument);
    }
}
