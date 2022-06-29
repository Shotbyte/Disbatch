package com.github.commandant.command.group;

import com.github.commandant.command.Command;
import com.github.commandant.command.parameter.ParameterUsage;
import com.github.commandant.command.parameter.ParameterizedCommand;
import com.github.commandant.command.parameter.builder.ParameterBuilder;
import com.github.commandant.command.parameter.builder.Suggesters;
import com.github.commandant.command.parameter.decorator.MutableParameter;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduces the concept of executing various commands belonging to a root command.
 *
 * @param <T> {@inheritDoc}
 */
public abstract class CommandGroup<T extends CommandSender> extends ParameterizedCommand<T, GroupedCommandExecutor<T>> {
    private final Map<String, GroupedCommand> commands = new HashMap<>();

    protected CommandGroup(final String subcommandLabel, final ParameterUsage usage) {
        this(new MutableParameter<>(), subcommandLabel, usage);
    }

    private CommandGroup(final MutableParameter<T, GroupedCommandExecutor<T>> parameter, final String subcommandLabel, final ParameterUsage usage) {
        super(parameter, usage);

        parameter.setUnderlyingParameter(ParameterBuilder.of(this)
                .predicate(boundedArgs -> commands.containsKey(boundedArgs[0]))
                .parser((boundedArgs, sender) -> new GroupedCommandExecutor<>(commands.get(boundedArgs[0]), copyShortened(boundedArgs)))
                .suggester(Suggesters.forFirstArgument(Suggesters.of(commands.keySet())))
                .usageLabels(subcommandLabel)
                .build());
    }

    /**
     * Adds a {@link Command} to be linked to this one.
     *
     * @param command the command to be linked
     */
    protected final void addCommand(final Command<? extends T> command) {
        commands.put(command.getLabel(), new GroupedCommand(command));
    }

    @Override
    protected final void execute(final T sender, final String commandLabel, final GroupedCommandExecutor<T> command) {
        command.execute(sender, commandLabel);
    }

    private String[] copyShortened(final String[] args) {
        return args.length >= 1 ? Arrays.copyOfRange(args, 1, args.length) : args;
    }
}
