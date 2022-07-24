package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.builder.Suggester;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationFromSenderParameter<T extends Entity> extends NumericalParameter<T, Location> {
    public LocationFromSenderParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    public LocationFromSenderParameter(final String xLabel, final String yLabel, final String zLabel, final Suggester<T> suggester) {
        super(suggester, xLabel, yLabel, zLabel);
    }

    @Override
    public Location parse(final CommandInput input, final T sender) {
        return new Location(sender.getWorld(),
                parseNumber(input.getArgument(0)),
                parseNumber(input.getArgument(1)),
                parseNumber(input.getArgument(2)));
    }
}
