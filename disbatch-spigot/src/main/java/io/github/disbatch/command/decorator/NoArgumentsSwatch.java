package io.github.disbatch.command.decorator;

import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.proxy.CommandProxy;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * @param <S> {@inheritDoc}
 */
public final class NoArgumentsSwatch<S extends CommandSender> extends CommandProxy<S> {
    private final Executor<S> executor;

    /**
     * @param innerCommand
     * @param executor
     */
    public NoArgumentsSwatch(final Command<S> innerCommand, final Executor<S> executor) {
        super(innerCommand);
        this.executor = executor;
    }

    @Override
    public void execute(final S sender, final @NotNull CommandInput input) {
        if (input.getArgumentLength() == 0) executor.execute(sender, input);
        else super.execute(sender, input);
    }

    /**
     * @param <S>
     */
    @FunctionalInterface
    public interface Executor<S extends CommandSender> {

        /**
         * @param sender
         * @param input
         */
        void execute(S sender, CommandInput input);
    }
}
