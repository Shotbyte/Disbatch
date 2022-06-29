package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationFromPlayerParameter extends NumericalParameter<Player, Location> {
    public LocationFromPlayerParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    public LocationFromPlayerParameter(final String xLabel, final String yLabel, final String zLabel, final Suggester<Player> suggester) {
        super(suggester, xLabel, yLabel, zLabel);
    }

    @Override
    public Location parse(final String[] boundedArgs, final Player sender) {
        return new Location(sender.getWorld(), parseNumber(boundedArgs[0]), parseNumber(boundedArgs[1]), parseNumber(boundedArgs[2]));
    }
}
