package com.github.commandant.command.enhancement.model;

import com.github.commandant.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 */
public interface CommandEnhancement {

    /**
     * @param command
     * @param <T>
     * @return
     */
    <T extends CommandSender> Command<T> applyTo(Command<T> command);
}
