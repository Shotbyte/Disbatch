package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationFromWorldParameter<S extends CommandSender> extends NumericalParameter<S, Location> {
    public LocationFromWorldParameter(final String worldNameLabel, final String xLabel, final String yLabel, final String zLabel) {
        super(worldNameLabel, xLabel, yLabel, zLabel);
    }

    @Override
    public Optional<Location> parse(final CommandInput input, final S sender) {
        final World world = Bukkit.getWorld(input.getArgument(0));
        return world == null ? Optional.empty() : numericOptional(input, createLocation(input, world));
    }

    @NotNull
    private Location createLocation(final CommandInput input, final World world) {
        return new Location(world,
                parseNumber(input.getArgument(0)),
                parseNumber(input.getArgument(1)),
                parseNumber(input.getArgument(2)));
    }
}
