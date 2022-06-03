package com.github.commandant.command.parameter.model;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationParameter extends NumericParameter<Player, Location> {
    public LocationParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    @Override
    public Location parse(final String[] args, final Player sender) {
        return new Location(sender.getWorld(), parseNumber(args[0]), parseNumber(args[1]), parseNumber(args[2]));
    }
}
