package com.github.commandant;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * @param <T>
 */
public interface CommandModel<T extends CommandSender> {

    /**
     * @param sender
     * @param args
     */
    void execute(T sender, String[] args);

    /**
     * @param sender
     * @param args
     * @return
     */
    default List<String> tabComplete(final T sender, final String[] args) {
        return Collections.emptyList();
    }

    /**
     * @return
     */
    String getLabel();
}
