package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.builder.Suggester;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
    public boolean canParse(final CommandInput input) {
        for (final String argument : input.getArguments()) {
            if (!canParseNumber(argument)) return false;
        }

        return !input.getArgument(0).isEmpty();
    }

    @Override
    public Optional<Location> parse(final CommandInput input, final T sender) {
        final Player player = Bukkit.getPlayer(input.getArgument(0));
        return player == null ? Optional.empty() : Optional.of(createLocation(input, player));
    }

    @NotNull
    private Location createLocation(final CommandInput input, final Player player) {
        return new Location(player.getWorld(),
                parseNumber(input.getArgument(1)),
                parseNumber(input.getArgument(2)),
                parseNumber(input.getArgument(3)));
    }
}
