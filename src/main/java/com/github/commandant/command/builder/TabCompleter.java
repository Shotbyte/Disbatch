package com.github.commandant.command.builder;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @param <T>
 */
public interface TabCompleter<T extends CommandSender> {

    /**
     * @param sender
     * @param args
     * @return
     */
    List<String> tabComplete(T sender, String[] args);
}
