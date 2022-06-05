package com.github.commandant;

import com.github.commandant.command.Command;
import com.github.commandant.command.proxy.TypedCommandProxy;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.List;

class CommandAdapter extends BukkitCommand {
    private final Command<CommandSender> command;

    CommandAdapter(final Command<?> command) {
        super(command.getLabel());
        this.command = new TypedCommandProxy(command);
    }

    @Override
    public boolean execute(final CommandSender sender, final String aliasLabel, final String[] args) {
        command.execute(sender, aliasLabel, args);
        return true;
    }

    @Override
    public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) {
        return command.tabComplete(sender, args);
    }
}
