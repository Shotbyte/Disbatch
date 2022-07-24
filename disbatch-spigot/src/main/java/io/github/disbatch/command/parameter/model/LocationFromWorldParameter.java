package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.builder.Suggester;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationFromWorldParameter<T extends CommandSender> extends NumericalParameter<T, Optional<Location>> {
    public LocationFromWorldParameter(final String worldNameLabel, final String xLabel, final String yLabel, final String zLabel) {
        super(worldNameLabel, xLabel, yLabel, zLabel);
    }

    public LocationFromWorldParameter(final String worldNameLabel, final String xLabel, final String yLabel, final String zLabel, final Suggester<T> suggester) {
        super(suggester, worldNameLabel, xLabel, yLabel, zLabel);
    }

    @Override
    public boolean canParse(final CommandInput input) {
        for (final String argument : input.getArguments())
            if (!canParseNumber(argument)) return false;

        return !input.getArgument(0).isEmpty();
    }

    @Override
    public Optional<Location> parse(final CommandInput input, final T sender) {
        final World world = Bukkit.getWorld(input.getArgument(0));
        return world == null ? Optional.empty() : Optional.of(createLocation(input, world));
    }

    @NotNull
    private Location createLocation(final CommandInput input, final World world) {
        return new Location(world,
                parseNumber(input.getArgument(0)),
                parseNumber(input.getArgument(1)),
                parseNumber(input.getArgument(2)));
    }
}
