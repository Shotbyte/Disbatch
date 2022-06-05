package com.github.commandant.command.proxy;

import com.github.commandant.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * A command abstraction for proxying any command.
 *
 * @param <T> {@inheritDoc}
 */
public abstract class CommandProxy<T extends CommandSender> implements Command<T> {
    protected final Command<T> innerCommand;

    protected CommandProxy(final Command<T> innerCommand) {
        this.innerCommand = innerCommand;
    }

    @Override
    public void execute(final T sender, final String commandLabel, final String[] args) {
        innerCommand.execute(sender, commandLabel, args);
    }

    @Override
    public List<String> tabComplete(final T sender, final String[] args) {
        return innerCommand.tabComplete(sender, args);
    }

    @Override
    public String getValidSenderMessage() {
        return innerCommand.getValidSenderMessage();
    }

    @Override
    public String getLabel() {
        return innerCommand.getLabel();
    }
}
