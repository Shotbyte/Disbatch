package io.github.disbatch;

import io.github.disbatch.command.CommandDescriptor;
import io.github.disbatch.command.CommandException;
import io.github.disbatch.command.CommandExecutionException;
import io.github.disbatch.command.proxy.TypedCommandProxy;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class LegacyCommandRegistrar implements CommandRegistrar {
    private final CommandMap serverCommandMap;

    LegacyCommandRegistrar(final Server server) {
        try {
            final PluginManager pluginManager = server.getPluginManager();
            final Optional<Field> fieldOptional = Arrays.stream(pluginManager.getClass().getDeclaredFields())
                    .filter(field -> CommandMap.class.isAssignableFrom(field.getType()))
                    .findFirst();

            if (fieldOptional.isPresent()) {
                final Field field = fieldOptional.get();
                field.setAccessible(true);
                serverCommandMap = (CommandMap) field.get(pluginManager);
            } else
                throw new CommandException("Server instance does not have a CommandMap");
        } catch (final ReflectiveOperationException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public void register(final TypedCommandProxy typedCommand, final CommandDescriptor descriptor) {
        final CommandAdapter adapter = new CommandAdapter(typedCommand, descriptor);
        serverCommandMap.register(adapter.getLabel(), adapter);
    }

    private static class CommandAdapter extends org.bukkit.command.Command {
        private final TypedCommandProxy typedCommand;

        private CommandAdapter(final TypedCommandProxy typedCommand, final CommandDescriptor descriptor) {
            super(descriptor.getLabel());
            this.typedCommand = typedCommand;

            setDescription(descriptor.getDescription());
            setAliases(descriptor.getAliases());
        }

        @Override
        public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
            if (sender == null)
                throw new CommandExecutionException("CommandSender is null");

            typedCommand.execute(sender, new LazyLoadingCommandInput(args, commandLabel));
            return true;
        }

        @Override
        public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) {
            return typedCommand.tabComplete(sender, new LazyLoadingCommandInput(args, alias));
        }

        @Override
        public String toString() {
            return typedCommand.toString();
        }
    }
}
