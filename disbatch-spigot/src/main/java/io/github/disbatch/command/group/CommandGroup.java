package io.github.disbatch.command.group;

import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandDescriptor;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.ParameterizedCommand;
import io.github.disbatch.command.parameter.builder.ParameterBuilder;
import io.github.disbatch.command.parameter.builder.Suggesters;
import io.github.disbatch.command.parameter.decorator.MutableParameter;
import io.github.disbatch.command.parameter.usage.ParameterUsage;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduces the concept from executing various commands belonging to a root command.
 *
 * @param <T> {@inheritDoc}
 */
public final class CommandGroup<T extends CommandSender> extends ParameterizedCommand<T, GroupedCommandExecutor<? super T>> {
    private final Map<String, Command<? super T>> commands = new HashMap<>();

    public CommandGroup(final String subcommandLabel, final ParameterUsage usage) {
        this(new MutableParameter<>(), subcommandLabel, usage);
    }

    private CommandGroup(final MutableParameter<T, GroupedCommandExecutor<? super T>> parameter, final String subcommandLabel, final ParameterUsage usage) {
        super(parameter, usage);

        parameter.setUnderlyingParameter(ParameterBuilder.of(this)
                .predicate(input -> commands.containsKey(input.getArgument(0)))
                .parser((input, sender) -> new GroupedCommandExecutor<>(commands.get(input.getArgument(0)), input))
                .suggester(Suggesters.forFirstArgument(Suggesters.of(commands.keySet())))
                .usageLabels(subcommandLabel)
                .build());
    }

    /**
     * Adds a {@link Command} to be linked to this one.
     *
     * @param command the command to be linked
     */
    public void addCommand(final Command<? super T> command, final CommandDescriptor descriptor) {
        commands.put(descriptor.getLabel(), command);

        for (final String alias : descriptor.getAliases())
            commands.put(alias, command);
    }

    @Override
    protected void execute(final T sender, final CommandInput input, final GroupedCommandExecutor<? super T> executor) {
        executor.execute(sender);
    }
}
