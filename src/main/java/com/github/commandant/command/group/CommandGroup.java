package com.github.commandant.command.group;

import com.github.commandant.command.Command;
import com.github.commandant.command.parameter.ParameterUsage;
import com.github.commandant.command.parameter.ParameterizedCommand;
import com.github.commandant.command.parameter.model.SenderIndependentParameter;
import org.bukkit.command.CommandSender;

import java.util.*;

/**
 * Introduces the concept of executing various commands belonging to a root command.
 *
 * @param <T> {@inheritDoc}
 */
public abstract class CommandGroup<T extends CommandSender> extends ParameterizedCommand<T, LinkedCommandExecutor<T>> {
    private final GroupedCommandParameter<T> parameter;

    protected CommandGroup(final String subcommandLabel, final ParameterUsage usage) {
        this(subcommandLabel, usage, null);
    }

    protected CommandGroup(final String subcommandLabel, final ParameterUsage usage, final String invalidCommandMessage) {
        this(new GroupedCommandParameter<>(subcommandLabel), usage, invalidCommandMessage);
    }

    private CommandGroup(final GroupedCommandParameter<T> parameter, final ParameterUsage usage, final String invalidCommandMessage) {
        super(parameter, usage, invalidCommandMessage);
        this.parameter = parameter;
    }

    /**
     * Adds a command to be linked to this one.
     *
     * @param command the command to be linked
     */
    @SuppressWarnings("unchecked")
    protected final void addCommand(final Command<? extends T> command) {
        parameter.commands.put(command.getLabel(), new LinkedCommand<>((Command<T>) command));
    }

    @Override
    protected final void execute(final T sender, final String commandLabel, final LinkedCommandExecutor<T> command) {
        command.execute(sender, commandLabel);
    }

    @Override
    public final List<String> tabComplete(final T sender, final String[] args) {
        return args.length > 0 && parameter.commands.containsKey(args[0])
                ? parameter.commands.get(args[0]).tabComplete(sender, truncate(args))
                : new LinkedList<>(parameter.commands.keySet());
    }

    private String[] truncate(final String[] args) {
        return args.length >= 1 ? Arrays.copyOfRange(args, 1, args.length) : args;
    }

    @Override
    protected final List<String> tabComplete(final T sender, final String argument) {
        return super.tabComplete(sender, argument);
    }

    private static class GroupedCommandParameter<T extends CommandSender> extends SenderIndependentParameter<LinkedCommandExecutor<T>> {
        private final Map<String, LinkedCommand<T>> commands = new HashMap<>();

        private GroupedCommandParameter(final String label) {
            super(label);
        }

        @Override
        public boolean canParse(final String[] args) {
            return commands.containsKey(args[0]);
        }

        @Override
        public int getUsageSpan() {
            return Integer.MAX_VALUE;
        }

        @Override
        protected LinkedCommandExecutor<T> parse(final String[] args) {
            return new LinkedCommandExecutor<>(commands.get(args[0]), Arrays.copyOfRange(args, 1, args.length));
        }
    }
}
