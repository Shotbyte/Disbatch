package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Retrieves an online {@link Player} by their name based on a parsable, passed argument.
 *
 * @param <S> {@inheritDoc}
 */
public final class PlayerFromNameParameter<S extends CommandSender> extends AbstractParameter<S, Player> {
    public PlayerFromNameParameter(final String playerNameLabel) {
        super(playerNameLabel);
    }

    @Override
    public Optional<Player> parse(final CommandInput input, final S sender) {
        return Optional.ofNullable(Bukkit.getPlayer(input.getArgument(0)));
    }
}
