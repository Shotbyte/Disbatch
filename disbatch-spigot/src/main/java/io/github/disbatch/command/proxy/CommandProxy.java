package io.github.disbatch.command.proxy;

import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * An abstraction for proxying any {@link Command}. Any method calls that should be delegated to the proxied command
 * can be done via the {@code super} reference (e.g., to forward execution logic, call
 * {@link Command#execute(CommandSender, CommandInput)} on it).
 *
 * @param <T> {@inheritDoc}
 */
public abstract class CommandProxy<T extends CommandSender> implements Command<T> {
    private final Command<T> innerCommand;

    protected CommandProxy(final @NotNull Command<T> innerCommand) {
        this.innerCommand = innerCommand;
    }

    @Override
    public void execute(final T sender, final @NotNull CommandInput input) {
        innerCommand.execute(sender, input);
    }

    @Override
    public List<String> tabComplete(final T sender, final @NotNull CommandInput input) {
        return innerCommand.tabComplete(sender, input);
    }

    @Override
    public String toString() {
        return innerCommand.toString();
    }
}
