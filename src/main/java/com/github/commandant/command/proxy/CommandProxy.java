package com.github.commandant.command.proxy;

import com.github.commandant.command.Command;
import lombok.experimental.Delegate;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * An abstraction for proxying any {@link Command}.
 *
 * @param <T> {@inheritDoc}
 */
public abstract class CommandProxy<T extends CommandSender> implements Command<T> {
    @Delegate
    private final Command<T> innerCommand;

    protected CommandProxy(@NotNull final Command<T> innerCommand) {
        this.innerCommand = innerCommand;
    }
}
