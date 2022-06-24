package com.github.commandant.command.builder;

import com.github.commandant.command.Command;
import com.google.common.base.Strings;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @param <T> any type extending CommandSender that is allowed to execute the built command
 */
public final class CommandBuilder<T extends CommandSender> {
    private CommandExecutor<T> executor;
    private TabCompleter<T> tabCompleter = TabCompletions.emptyTabCompleter();
    private String validSenderMessage;
    private String label;

    public CommandBuilder<T> executor(final CommandExecutor<T> executor) {
        this.executor = executor;
        return this;
    }

    public CommandBuilder<T> tabCompleter(final TabCompleter<T> tabCompleter) {
        this.tabCompleter = tabCompleter;
        return this;
    }

    public CommandBuilder<T> validSenderMessage(final String validSenderMessage) {
        this.validSenderMessage = validSenderMessage;
        return this;
    }

    public CommandBuilder<T> label(final String label) {
        this.label = label;
        return this;
    }

    public Command<T> build() {
        return new BuiltCommand(executor, tabCompleter, validSenderMessage, label);
    }

    private class BuiltCommand implements Command<T> {
        private final CommandExecutor<T> executor;
        private final TabCompleter<T> tabCompleter;
        private final String validSenderMessage;
        private final String label;

        private BuiltCommand(@NotNull final CommandExecutor<T> executor, @NotNull final TabCompleter<T> tabCompleter, final String validSenderMessage, @NotNull final String label) {
            this.executor = executor;
            this.tabCompleter = tabCompleter;
            this.validSenderMessage = validSenderMessage;
            this.label = label;
        }

        @Override
        public void execute(final T sender, final String commandLabel, final String[] args) {
            executor.execute(sender, commandLabel, args);
        }

        @Override
        public List<String> tabComplete(final T sender, final String[] args) {
            return tabCompleter.tabComplete(sender, args);
        }

        @Override
        public String getValidSenderMessage() {
            return Strings.nullToEmpty(validSenderMessage);
        }

        @Override
        public String getLabel() {
            return label;
        }
    }
}
