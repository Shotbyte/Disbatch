package com.github.commandant.command.parameter;

import com.github.commandant.command.Command;
import org.bukkit.command.CommandSender;

import java.util.*;

/**
 * @param <T>
 */
public abstract class CommandGroup<T extends CommandSender> extends ParameterizedCommand<T, Command<CommandSender>> {
    private final Map<String, SubcommandContainer> subcommands = new HashMap<>();
    private final SubcommandParameter parameter;

    protected CommandGroup(final SubcommandParameter parameter, final ParameterUsage usage) {
        this(parameter, usage, null);
    }

    protected CommandGroup(final SubcommandParameter parameter, final ParameterUsage usage, final String invalidSubcommandMessage) {
        super(parameter, usage, invalidSubcommandMessage);
        this.parameter = parameter;
        parameter.setSubcommands(subcommands);
    }

    @Override
    protected final void execute(final T sender, final String aliasLabel, final Command<CommandSender> subcommand) {
        final String[] lastArgs = parameter.getLastParsedArgs();
        final String[] newArgs = Arrays.copyOfRange(lastArgs, 1, lastArgs.length - 1);
        subcommand.execute(sender, aliasLabel + " " + subcommand.getLabel(), newArgs);
    }

    @Override
    protected final List<String> tabComplete(final CommandSender sender, final String argument) {
        return new LinkedList<>(subcommands.keySet());
    }

    /**
     * @param command
     */
    protected final void addSubcommand(final Command<?> command) {
        subcommands.put(command.getLabel(), new SubcommandContainer(command));
    }
}