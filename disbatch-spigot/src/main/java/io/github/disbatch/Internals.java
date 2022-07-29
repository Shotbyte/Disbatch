package io.github.disbatch;

import io.github.disbatch.command.CommandDescriptor;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.exception.CommandRegistrationException;
import org.bukkit.command.PluginCommand;

final class Internals {
    private Internals() {
        throw new AssertionError();
    }

    static CommandInput computeInput(final String label, final String[] args) {
        return args.length > 0 ? new LazyLoadingCommandInput(args, label) : new SingleLabelCommandInput(label);
    }

    static PluginCommand checkPluginCommand(final PluginCommand pluginCommand, final CommandDescriptor descriptor) {
        if (pluginCommand == null)
            throw new CommandRegistrationException(String.format("Command \"%s\" not found in plugin.yml file",
                    descriptor.getLabel()));

        return pluginCommand;
    }
}
