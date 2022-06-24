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
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
 * A namespace for anything related to registering a {@link Command}.
 */
@UtilityClass
public class Commandant {
    private final CommandMap SERVER_COMMAND_MAP = getServerCommandMap();

    /**
     * @param command
     * @param plugin
     */
    public void register(@NotNull final Command<?> command, @NotNull final JavaPlugin plugin) {
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
     */
    public void register(@NotNull final Command<?> command) {
        register(new CommandAdapter(command));
    }

    public void register(@NotNull final Command<?> command, @NotNull final CommandDescriptor descriptor) {
        register(new IdentifiableCommandAdapter(command, descriptor));
    }

    private void register(final CommandAdapter adapter) {
        SERVER_COMMAND_MAP.register(adapter.getLabel(), adapter);
    }

    private CommandMap getServerCommandMap() {
        try {
            final Field field = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(Bukkit.getPluginManager());
        } catch (final IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }
}
