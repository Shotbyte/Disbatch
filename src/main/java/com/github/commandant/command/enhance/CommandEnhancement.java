package com.github.commandant.command.enhance;

import com.github.commandant.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandEnhancement {
    <T extends CommandSender> Command<T> applyTo(Command<T> command);
}
