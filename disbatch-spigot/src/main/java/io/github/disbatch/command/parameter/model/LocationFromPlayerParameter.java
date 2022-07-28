package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Creates a {@link Location} based on parsable, passed arguments.
 */
public final class LocationFromPlayerParameter<S extends CommandSender> extends NumericalParameter<S, Location> {
    public LocationFromPlayerParameter(final String playerNameLabel, final String xLabel, final String yLabel, final String zLabel) {
        super(playerNameLabel, xLabel, yLabel, zLabel);
    }

    @Override
    public Optional<Location> parse(final CommandInput input, final S sender) {
        final Player player = Bukkit.getPlayer(input.getArgument(0));
        return player == null ? Optional.empty() : numericOptional(input, createLocation(input, player));
    }

    @NotNull
    private Location createLocation(final CommandInput input, final Player player) {
        return new Location(player.getWorld(),
                parseNumber(input.getArgument(1)),
                parseNumber(input.getArgument(2)),
                parseNumber(input.getArgument(3)));
    }
}
