package io.github.disbatch.command.decorator;

import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.proxy.CommandProxy;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * @param <T> {@inheritDoc}
 */
public final class NoArgumentsSwatch<T extends CommandSender> extends CommandProxy<T> {
    private final Executor<T> executor;

    /**
     * @param innerCommand
     * @param executor
     */
    public NoArgumentsSwatch(final Command<T> innerCommand, final Executor<T> executor) {
        super(innerCommand);
        this.executor = executor;
    }

    @Override
    public void execute(final T sender, final @NotNull CommandInput input) {
        if (input.getArgumentLength() == 0) executor.execute(sender, input);
        else super.execute(sender, input);
    }

    /**
     * @param <T>
     */
    @FunctionalInterface
    public interface Executor<T extends CommandSender> {

        /**
         * @param sender
         * @param input
         */
        void execute(T sender, CommandInput input);
    }
}
