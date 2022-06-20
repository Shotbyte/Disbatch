package com.github.commandant.command.enhancement.model;

import com.github.commandant.command.Command;
import com.github.commandant.command.proxy.CommandProxy;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @see Executor
 */
public class NoArgumentsExecutionEnhancement implements CommandEnhancement {
    private final Map<Class<?>, Executor<CommandSender>> executors = new HashMap<>();

    @Override
    public <T extends CommandSender> Command<T> applyTo(final Command<T> command) {
        return new NoArgumentsExecutionCommand<>(command);
    }

    /**
     * @param executor
     * @return
     */
    public NoArgumentsExecutionEnhancement withExecutor(final Executor<CommandSender> executor) {
        return withExecutor(CommandSender.class, executor);
    }

    /**
     * @param senderType
     * @param executor
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T extends CommandSender> NoArgumentsExecutionEnhancement withExecutor(final Class<T> senderType, final Executor<T> executor) {
        executors.put(senderType, Objects.requireNonNull((Executor<CommandSender>) executor));
        return this;
    }

    /**
     *
     */
    @FunctionalInterface
    public interface Executor<T extends CommandSender> {
        void executeNoArgs(T sender, String commandLabel);
    }

    private class NoArgumentsExecutionCommand<T extends CommandSender> extends CommandProxy<T> {
        private NoArgumentsExecutionCommand(final Command<T> innerCommand) {
            super(innerCommand);
        }

        @Override
        public void execute(final T sender, final String commandLabel, final String[] args) {
            Executor<CommandSender> executor = null;

            for (final Map.Entry<Class<?>, Executor<CommandSender>> entry : executors.entrySet()) {
                if (entry.getKey().isAssignableFrom(sender.getClass())) {
                    executor = entry.getValue();
                    break;
                }
            }

            if (executor != null && args.length == 0) executor.executeNoArgs(sender, commandLabel);
            else innerCommand.execute(sender, commandLabel, args);
        }
    }
}
