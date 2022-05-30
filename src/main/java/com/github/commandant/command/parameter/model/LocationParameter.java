package com.github.commandant.command.parameter.model;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationParameter extends AbstractParameter<Player, Location> {
    public LocationParameter(final String label) {
        super(label);
    }

    @Override
    public boolean canParse(final String[] args) {
        return false;
    }

    @Override
    public Location parse(final String[] args, final Player sender) {
        return new Location(sender.getWorld(),
                parseDouble(args[0]),
                parseDouble(args[1]),
                parseDouble(args[2]));
    }

    private double parseDouble(final String argument) {
        return Double.parseDouble(argument);
    }

    @Override
    public int getSize() {
        return 3;
    }
}
