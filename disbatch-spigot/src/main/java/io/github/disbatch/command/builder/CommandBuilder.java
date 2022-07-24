package io.github.disbatch.command.builder;

import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandInput;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Serves as a flexible solution for creating a new {@link Command} without defining an anonymous or explicit abstraction.
 *
 * @param <T> any type extending CommandSender that is allowed to execute the built command
 */
public final class CommandBuilder<T extends CommandSender> {
    private CommandExecutor<T> executor;
    private TabCompleter<T> tabCompleter = TabCompletions.emptyTabCompleter();
    private String validSenderMessage = StringUtils.EMPTY;

    public CommandBuilder<T> executor(final @NotNull CommandExecutor<T> executor) {
        this.executor = executor;
        return this;
    }

    public CommandBuilder<T> tabCompleter(final @NotNull TabCompleter<T> tabCompleter) {
        this.tabCompleter = tabCompleter;
        return this;
    }

    public CommandBuilder<T> validSenderMessage(final @NotNull String validSenderMessage) {
        this.validSenderMessage = validSenderMessage;
        return this;
    }

    /**
     * Creates a new {@link Command}.
     *
     * @return the created command
     */
    public Command<T> build() {
        return new BuiltCommand(executor, tabCompleter, validSenderMessage);
    }

    private class BuiltCommand implements Command<T> {
        private final CommandExecutor<T> executor;
        private final TabCompleter<T> tabCompleter;
        private final String validSenderMessage;

        private BuiltCommand(final @NotNull CommandExecutor<T> executor, final @NotNull TabCompleter<T> tabCompleter, final @NotNull String validSenderMessage) {
            this.executor = executor;
            this.tabCompleter = tabCompleter;
            this.validSenderMessage = validSenderMessage;
        }

        @Override
        public void execute(final T sender, final @NotNull CommandInput input) {
            executor.execute(sender, input);
        }

        @Override
        public List<String> tabComplete(final T sender, final @NotNull CommandInput input) {
            return tabCompleter.tabComplete(sender, input);
        }

    }
}
