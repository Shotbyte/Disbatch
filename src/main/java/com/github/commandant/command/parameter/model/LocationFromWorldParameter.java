package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
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
    public boolean canParse(final String[] args) {
        return !args[0].isEmpty() && super.canParse(Arrays.copyOfRange(args, 1, args.length));
    }

    @Override
    public Optional<Location> parse(final String[] boundedArgs, final T sender) {
        final World world = Bukkit.getWorld(boundedArgs[0]);
        return world == null ? Optional.empty() : Optional.of(createLocation(boundedArgs, world));
    }

    @NotNull
    private Location createLocation(final String[] boundedArgs, final World world) {
        return new Location(world, parseNumber(boundedArgs[1]), parseNumber(boundedArgs[2]), parseNumber(boundedArgs[3]));
    }
}
