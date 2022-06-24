package com.github.commandant.command.parameter.model;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * Retrieves an {@link OfflinePlayer} based on a parsable, passed argument.
 *
 * @param <T> {@inheritDoc}
 */
public final class OfflinePlayerParameter<T extends CommandSender> extends UUIDOrientedParameter<T, OfflinePlayer> {
    public OfflinePlayerParameter(final String usageLabel) {
        super(usageLabel);
    }

    @Override
    public OfflinePlayer parse(final String[] args, final T sender) {
        return Bukkit.getOfflinePlayer(UUID.fromString(args[0]));
    }
}
