package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.builder.Suggester;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationFromBlockSenderParameter extends NumericalParameter<BlockCommandSender, Location> {
    public LocationFromBlockSenderParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    public LocationFromBlockSenderParameter(final String xLabel, final String yLabel, final String zLabel, final Suggester<BlockCommandSender> suggester) {
        super(suggester, xLabel, yLabel, zLabel);
    }

    @Override
    public Location parse(final CommandInput input, final BlockCommandSender sender) {
        return new Location(sender.getBlock().getWorld(),
                parseNumber(input.getArgument(1)),
                parseNumber(input.getArgument(2)),
                parseNumber(input.getArgument(3)));
    }
}
