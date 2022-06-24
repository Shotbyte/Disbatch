package com.github.commandant.command.builder;

import org.bukkit.command.CommandSender;

/**
 * @param <T>
 */
public interface CommandExecutor<T extends CommandSender> {

    /**
     * @param sender
     * @param commandLabel
     * @param args
     */
    void execute(T sender, String commandLabel, String[] args);
}
