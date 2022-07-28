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
import java.util.Optional;

/**
 * Introduces the concept from executing various commands belonging to a root command.
 *
 * @param <S> {@inheritDoc}
 */
public final class CommandGroup<S extends CommandSender> extends ParameterizedCommand<S, GroupedCommandExecutor<? super S>> {
    private final Map<String, GroupedCommand<? super S>> commands = new HashMap<>();

    public CommandGroup(final String subcommandLabel, final ParameterUsage usage) {
        this(new MutableParameter<>(), subcommandLabel, usage);
    }

    private CommandGroup(final MutableParameter<S, GroupedCommandExecutor<? super S>> parameter, final String subcommandLabel, final ParameterUsage usage) {
        super(parameter, usage);

        parameter.setUnderlyingParameter(ParameterBuilder.of(this)
                .parser((input, sender) -> {
                    final GroupedCommand<? super S> groupedCommand = commands.get(input.getArgument(0));
                    return groupedCommand == null ? Optional.empty() : Optional.of(new GroupedCommandExecutor<>(groupedCommand, input));
                })
                .suggester(Suggesters.forFirstArgument(Suggesters.of(commands.keySet())))
                .usageLabels(subcommandLabel)
                .build());
    }

    /**
     * Adds a {@link Command} to be linked to this one.
     *
     * @param command the command to be linked
     */
    public void addCommand(final Command<? super S> command, final CommandDescriptor descriptor) {
        commands.put(descriptor.getLabel(), new GroupedCommand<>(command, descriptor.getLabel()));

        for (final String alias : descriptor.getAliases())
            commands.put(alias, new GroupedCommand<>(command, alias));
    }

    @Override
    protected void execute(final S sender, final CommandInput input, final GroupedCommandExecutor<? super S> executor) {
        executor.execute(sender);
    }
}
