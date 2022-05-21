package com.github.commandant;

import lombok.experimental.UtilityClass;
import net.jodah.typetools.TypeResolver;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A namespace for registering a {@link CommandModel}.
 */
@UtilityClass
public class Commandant {

    /**
     * @param command
     * @param plugin
     */
    @SuppressWarnings("unchecked")
    public void register(final CommandModel<?> command, final JavaPlugin plugin) {
        final PluginCommand pluginCommand = plugin.getCommand(command.getLabel());
        final CommandModel<CommandSender> castedCommand = (CommandModel<CommandSender>) command;
        final Class<?> senderType = TypeResolver.resolveRawArgument(CommandModel.class, castedCommand.getClass());

        pluginCommand.setExecutor((sender, serverCommand, label, args) -> {
            if (senderType.isInstance(sender.getClass()))
                castedCommand.execute(sender, args);
            return true;
        });

        pluginCommand.setTabCompleter((sender, serverCommand, alias, args) -> castedCommand.tabComplete(sender, args));
    }

    /**
     * @param command
     * @param map
     */
    public void register(final CommandModel<?> command, final CommandMap map) {
        map.register(command.getLabel(), "", new CommandModelAdapter(command));
    }
}
