package com.github.commandant.command.parameter;

import com.github.commandant.command.Command;
import com.google.common.base.Strings;
import org.bukkit.command.CommandSender;

import java.util.*;

/**
 * @param <T>
 */
public abstract class CommandGroup<T extends CommandSender> implements Command<T> {
    private final Map<String, Command<T>> subcommands = new HashMap<>();
    private final String[] singletonSubcommandLabel;
    private final ParameterUsage usage;
    private final String invalidSubcommandMessage;

    protected CommandGroup(final String subcommandLabel, final ParameterUsage usage) {
        this(subcommandLabel, usage, null);
    }

    protected CommandGroup(final String subcommandLabel, final ParameterUsage usage, final String invalidSubcommandMessage) {
        this.usage = usage;
        singletonSubcommandLabel = new String[]{subcommandLabel};
        this.invalidSubcommandMessage = invalidSubcommandMessage;
    }

    @Override
    public final void execute(final T sender, final String aliasLabel, final String[] args) {
        if (args.length != 1)
            sender.sendMessage(usage.toMessage(aliasLabel, singletonSubcommandLabel));
        else {
            final Command<T> command = subcommands.get(args[0]);

            if (command != null)
                command.execute(sender, aliasLabel + " " + command.getLabel(), Arrays.copyOfRange(args, 1, args.length));
            else if (Strings.isNullOrEmpty(invalidSubcommandMessage))
                sender.sendMessage(invalidSubcommandMessage);
            else
                sender.sendMessage(usage.toMessage(aliasLabel, singletonSubcommandLabel));
        }
    }

    @Override
    public final List<String> tabComplete(final T sender, final String[] args) {
        if (args.length == 1) {
            final Command<T> subcommand = subcommands.get(args[args.length - 1]);

            return subcommand == null
                    ? new LinkedList<>(subcommands.keySet())
                    : subcommand.tabComplete(sender, Arrays.copyOfRange(args, 1, args.length));
        }

        return Collections.emptyList();
    }

    protected void addSubcommand(final Command<T> command) {
        subcommands.put(command.getLabel(), command);
    }
}