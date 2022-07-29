package io.github.disbatch;

import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandDescriptor;
import io.github.disbatch.command.proxy.TypedCommandProxy;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * A namespace for anything related to registering a {@link Command}.
 */
public final class Disbatch {
    private static final CommandRegistrar REGISTRAR = new LegacyCommandRegistrar(Bukkit.getServer());

    private Disbatch() {
        throw new AssertionError();
    }

    /**
     * @param command
     * @param descriptor
     */
    public static void register(final @NotNull Command<?> command, final @NotNull CommandDescriptor descriptor) {
        REGISTRAR.register(new TypedCommandProxy(command, descriptor.getValidSenderMessage()), descriptor);
    }

    /**
     * @param command
     * @param descriptor
     * @param plugin
     */
    public static void register(final @NotNull Command<?> command, final @NotNull CommandDescriptor descriptor, final @NotNull JavaPlugin plugin) {
        final PluginCommand pluginCommand = Internals.checkPluginCommand(plugin.getCommand(descriptor.getLabel()), descriptor);
        final TypedCommandProxy proxy = new TypedCommandProxy(command, descriptor.getValidSenderMessage());

        pluginCommand.setExecutor((sender, serverCommand, label, args) -> {
            proxy.execute(sender, Internals.computeInput(label, args));
            return true;
        });

        pluginCommand.setTabCompleter((sender, serverCommand, label, args)
                -> proxy.tabComplete(sender, Internals.computeInput(label, args)));
    }
}
