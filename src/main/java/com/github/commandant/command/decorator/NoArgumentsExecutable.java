package com.github.commandant.command.decorator;

import com.github.commandant.command.Command;
import com.github.commandant.command.proxy.CommandProxy;
import org.bukkit.command.CommandSender;

/**
 * @param <T>
 */
public final class NoArgumentsExecutable<T extends CommandSender> extends CommandProxy<T> {
    private final NoArgumentsExecutor<T> executor;

    public NoArgumentsExecutable(final Command<T> innerCommand, final NoArgumentsExecutor<T> executor) {
        super(innerCommand);
        this.executor = executor;
    }

    @Override
    public void execute(final T sender, final String commandLabel, final String[] args) {
        if (args.length == 0) executor.execute(sender, commandLabel);
        else super.execute(sender, commandLabel, args);
    }
}
