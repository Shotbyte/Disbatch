package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

/**
 * Retrieves an online {@link Player} by their {@link UUID} based on a parsable, passed argument.
 *
 * @param <S> {@inheritDoc}
 */
public final class PlayerFromUUIDParameter<S extends CommandSender> extends UUIDOrientedParameter<S, Player> {

    /**
     * @param uuidLabel
     */
    public PlayerFromUUIDParameter(final String uuidLabel) {
        super(uuidLabel);
    }

    @Override
    public Optional<Player> parse(final CommandInput input, final S sender) {
        final String arg = input.getArgument(0);

        return isUniqueId(arg)
                ? Optional.ofNullable(Bukkit.getPlayer(UUID.fromString(arg)))
                : Optional.empty();
    }
}
