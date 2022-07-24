package io.github.disbatch.command.builder;

import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;

/**
 * @param <T>
 */
@FunctionalInterface
public interface CommandExecutor<T extends CommandSender> {

    /**
     * @param sender
     * @param input
     */
    void execute(T sender, final CommandInput input);
}
