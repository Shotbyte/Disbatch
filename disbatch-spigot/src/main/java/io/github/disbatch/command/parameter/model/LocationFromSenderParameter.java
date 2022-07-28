package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.Optional;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationFromSenderParameter<S extends Entity> extends NumericalParameter<S, Location> {
    public LocationFromSenderParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    @Override
    public Optional<Location> parse(final CommandInput input, final S sender) {
        return numericOptional(input, new Location(sender.getWorld(),
                parseNumber(input.getArgument(0)),
                parseNumber(input.getArgument(1)),
                parseNumber(input.getArgument(2))));
    }
}
