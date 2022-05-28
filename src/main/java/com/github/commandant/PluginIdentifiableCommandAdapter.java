package com.github.commandant;

import com.github.commandant.command.IdentifiableCommand;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

class PluginIdentifiableCommandAdapter extends IdentifiableCommandAdapter implements PluginIdentifiableCommand {
    private final Plugin plugin;

    PluginIdentifiableCommandAdapter(final IdentifiableCommand<?> command, final Plugin plugin) {
        super(command);
        this.plugin = plugin;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
