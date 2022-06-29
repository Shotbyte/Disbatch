package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.Optional;

/**
 * Retrieves a {@link World} based on a parsable, passed argument.
 */
public final class WorldParameter<T extends CommandSender> extends StringParsableParameter<T, Optional<World>> {
    public WorldParameter(final String worldNameLabel) {
        super(worldNameLabel);
    }

    public WorldParameter(final String worldNameLabel, final Suggester<T> suggester) {
        super(suggester, worldNameLabel);
    }

    @Override
    public Optional<World> parse(final String[] args, final T sender) {
        return Optional.ofNullable(Bukkit.getWorld(args[0]));
    }
}
