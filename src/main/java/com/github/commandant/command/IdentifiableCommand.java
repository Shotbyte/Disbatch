package com.github.commandant.command;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @param <T>
 */
public interface IdentifiableCommand<T extends CommandSender> extends Command<T> {

    /**
     * @return
     */
    String getDescription();

    /**
     * @return
     */
    List<String> getAliases();
}
