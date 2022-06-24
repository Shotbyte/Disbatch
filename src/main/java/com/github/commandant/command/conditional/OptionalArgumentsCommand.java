package com.github.commandant.command.conditional;

import com.github.commandant.command.Command;
import com.github.commandant.command.proxy.CommandProxy;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @param <T> {@inheritDoc}
 */
public abstract class OptionalArgumentsCommand<T extends CommandSender> extends CommandProxy<T> {
    protected OptionalArgumentsCommand(final Command<T> innerCommand) {
        super(innerCommand);
    }

    @Override
    public final void execute(final T sender, final String commandLabel, final String[] args) {
        if (args.length == 0) executeNoArgs(sender, commandLabel);
        else super.execute(sender, commandLabel, args);
    }

    /**
     * @param sender
     * @param commandLabel
     */
    protected abstract void executeNoArgs(final T sender, final String commandLabel);

    @Override
    public final List<String> tabComplete(final T sender, final String[] args) {
        return super.tabComplete(sender, args);
    }

    @Override
    public final String getValidSenderMessage() {
        return super.getValidSenderMessage();
    }

    @Override
    public final String getLabel() {
        return super.getLabel();
    }
}
