package com.github.commandant.command;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @param <T>
 */
public abstract class CommandProxy<T extends CommandSender> implements Command<T> {
    protected final Command<T> innerCommand;

    protected CommandProxy(final Command<T> innerCommand) {
        this.innerCommand = innerCommand;
    }

    @Override
    public void execute(final T sender, final String aliasLabel, final String[] args) {
        innerCommand.execute(sender, aliasLabel, args);
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
