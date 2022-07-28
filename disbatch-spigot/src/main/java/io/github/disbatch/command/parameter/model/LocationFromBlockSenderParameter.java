package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;

import java.util.Optional;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationFromBlockSenderParameter extends NumericalParameter<BlockCommandSender, Location> {
    public LocationFromBlockSenderParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    @Override
    public Optional<Location> parse(final CommandInput input, final BlockCommandSender sender) {
        return numericOptional(input, new Location(sender.getBlock().getWorld(),
                parseNumber(input.getArgument(0)),
                parseNumber(input.getArgument(1)),
                parseNumber(input.getArgument(2))));
    }
}
