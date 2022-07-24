package io.github.disbatch.command.builder;

import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @param <T>
 */
@FunctionalInterface
public interface TabCompleter<T extends CommandSender> {

    /**
     * @param sender
     * @param input
     * @return
     */
    List<String> tabComplete(T sender, CommandInput input);
}
