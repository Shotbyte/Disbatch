package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggesters;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationByPlayerWorldParameter extends NumericalParameter<Player, Location> {
    public LocationByPlayerWorldParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(Suggesters.emptySuggester(), xLabel, yLabel, zLabel);
    }

    @Override
    public Location parse(final String[] boundedArgs, final Player sender) {
        return new Location(sender.getWorld(), parseNumber(boundedArgs[0]), parseNumber(boundedArgs[1]), parseNumber(boundedArgs[2]));
    }
}
