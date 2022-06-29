package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationFromPlayerParameter<T extends CommandSender> extends NumericalParameter<T, Optional<Location>> {
    public LocationFromPlayerParameter(final String playerNameLabel, final String xLabel, final String yLabel, final String zLabel) {
        super(playerNameLabel, xLabel, yLabel, zLabel);
    }

    public LocationFromPlayerParameter(final String playerNameLabel, final String xLabel, final String yLabel, final String zLabel, final Suggester<T> suggester) {
        super(suggester, playerNameLabel, xLabel, yLabel, zLabel);
    }

    @Override
    public boolean canParse(final String[] args) {
        return !args[0].isEmpty() && super.canParse(Arrays.copyOfRange(args, 1, args.length));
    }

    @Override
    public Optional<Location> parse(final String[] boundedArgs, final T sender) {
        final Player player = Bukkit.getPlayer(boundedArgs[0]);
        return player == null ? Optional.empty() : Optional.of(createLocation(boundedArgs, player));
    }

    @NotNull
    private Location createLocation(final String[] boundedArgs, final Player player) {
        return new Location(player.getWorld(), parseNumber(boundedArgs[1]), parseNumber(boundedArgs[2]), parseNumber(boundedArgs[3]));
    }
}
