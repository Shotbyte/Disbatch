package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.Optional;

/**
 * Retrieves a {@link World} based on a parsable, passed argument.
 */
public final class WorldParameter<S extends CommandSender> extends AbstractParameter<S, World> {
    public WorldParameter(final String worldNameLabel) {
        super(worldNameLabel);
    }

    @Override
    public Optional<World> parse(final CommandInput input, final S sender) {
        return Optional.ofNullable(Bukkit.getWorld(input.getArgument(0)));
    }
}
