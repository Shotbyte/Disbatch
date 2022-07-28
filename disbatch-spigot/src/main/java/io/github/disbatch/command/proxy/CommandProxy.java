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
 * @param <S> {@inheritDoc}
 */
public abstract class CommandProxy<S extends CommandSender> implements Command<S> {
    private final Command<S> innerCommand;

    protected CommandProxy(final @NotNull Command<S> innerCommand) {
        this.innerCommand = innerCommand;
    }

    @Override
    public void execute(final S sender, final @NotNull CommandInput input) {
        innerCommand.execute(sender, input);
    }

    @Override
    public List<String> tabComplete(final S sender, final @NotNull CommandInput input) {
        return innerCommand.tabComplete(sender, input);
    }

    @Override
    public String toString() {
        return innerCommand.toString();
    }
}
