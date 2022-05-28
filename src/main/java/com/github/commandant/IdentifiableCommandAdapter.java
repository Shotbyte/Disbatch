package com.github.commandant;

import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

class IdentifiableCommandAdapter extends CommandAdapter implements PluginIdentifiableCommand {
    private final Plugin plugin;

    IdentifiableCommandAdapter(final IdentifiableCommand<?> command, final Plugin plugin) {
        super(command);
        this.plugin = plugin;

        setAliases(command.getAliases());
        setDescription(command.getDescription());
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
