package com.github.commandant;

import com.github.commandant.command.Command;
import com.github.commandant.command.CommandDescriptor;
import com.github.commandant.command.proxy.TypedCommandProxy;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

/**
 * A namespace for anything related to registering a {@link Command}.
 */
@UtilityClass
public class Commandant {

    /**
     * @param command
     * @param plugin
     */
    public void register(final Command<?> command, final JavaPlugin plugin) {
        final PluginCommand pluginCommand = plugin.getCommand(command.getLabel());
        final Command<CommandSender> proxy = new TypedCommandProxy(command);

        pluginCommand.setExecutor((sender, serverCommand, aliasLabel, args) -> {
            proxy.execute(sender, aliasLabel, args);
            return true;
        });

        pluginCommand.setTabCompleter((sender, serverCommand, alias, args) -> proxy.tabComplete(sender, args));
    }

    /**
     * @param command
     * @param map
     */
    public void register(final Command<?> command, final CommandMap map) {
        register(new CommandAdapter(command), map);
    }

    public void register(final Command<?> command, final CommandDescriptor descriptor, final CommandMap map) {
        register(new IdentifiableCommandAdapter(command, descriptor), map);
    }

    private void register(final CommandAdapter adapter, final CommandMap map) {
        map.register(adapter.getLabel(), adapter);
    }

    /**
     * @return
     */
    public CommandMap getServerCommandMap() {
        try {
            final Field field = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(Bukkit.getPluginManager());
        } catch (final IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }
}
