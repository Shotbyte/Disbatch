package com.github.commandant.command;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * @param <T>
 */
public interface Command<T extends CommandSender> {

    /**
     * @param sender
     * @param aliasLabel
     * @param args
     */
    void execute(T sender, final String aliasLabel, String[] args);

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
