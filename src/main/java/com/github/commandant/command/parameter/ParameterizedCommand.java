package com.github.commandant.command.parameter;

import com.github.commandant.command.Command;
import com.github.commandant.command.builder.TabCompletions;
import com.github.commandant.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Introduces the concept of effortlessly transforming a specific set of arguments of a compatible length into a usable
 * {@link Object} during execution.
 *
 * @param <K> {@inheritDoc}
 * @param <V> the type of the resulting {@link Object} parsed from a set of arguments
 * @see #execute(CommandSender, String, Object)
 * @see #onInvalidInput(CommandSender, String)
 * @see Parameter
 * @see ParameterUsage
 */
public abstract class ParameterizedCommand<K extends CommandSender, V> implements Command<K> {
    private final Parameter<? super K, V> parameter;
    private final ParameterUsage usage;

    /**
     * Constructs a new {@link ParameterizedCommand}.
     *
     * @param parameter the parameter to use for {@link Object} argument creation
     * @param usage     the usage to use for creating usage messages when necessary
     *                  arguments (the normal usage message will be sent instead if null or empty)
     * @throws ParameterOutOfBoundsException if the passed parameter's minimum or maximum usages return {@code 0} or
     *                                       exceed one another
     */
    protected ParameterizedCommand(@NotNull final Parameter<? super K, V> parameter, @NotNull final ParameterUsage usage) {
        validateCorrectBounds(parameter);

        this.parameter = parameter;
        this.usage = usage;
    }

    private void validateCorrectBounds(final Parameter<?, ?> parameter) {
        final int minUsage = parameter.getMinimumUsage();
        final int maxUsage = parameter.getMaximumUsage();

        if (minUsage <= 0) throw new ParameterOutOfBoundsException("Minimum usage must be greater than 0");
        if (maxUsage <= 0) throw new ParameterOutOfBoundsException("Maximum usage must be greater than 0");
        if (minUsage > maxUsage) throw new ParameterOutOfBoundsException("Minimum usage cannot exceed maximum usage");
    }

    @Override
    public final void execute(final K sender, final String commandLabel, final String[] args) {
        final boolean isCompatibleLength = args.length >= parameter.getMinimumUsage() && args.length <= parameter.getMaximumUsage();

        if (isCompatibleLength && parameter.canParse(args)) {
            final V result = Objects.requireNonNull(parameter.parse(args, sender),
                    "Parsed result from input is null (parameter: " + parameter + ")");
            execute(sender, commandLabel, result);
        } else if (!isCompatibleLength)
            sender.sendMessage(usage.toMessage(commandLabel, parameter.getUsageLabels()));
        else
            onInvalidInput(sender, commandLabel);
    }

    /**
     * Executed for when a valid {@link CommandSender} passes invalid input. By default, this sends the sender a usage
     * message created from the underlying {@link ParameterUsage}.
     *
     * @param sender       the source responsible for passing invalid input
     * @param commandLabel the alias of the command used
     */
    protected void onInvalidInput(final K sender, final String commandLabel) {
        sender.sendMessage(usage.toMessage(commandLabel, parameter.getUsageLabels()));
    }

    /**
     * Serves the same functionality as {@link Command#execute(CommandSender, String, String[])} but with a resulting
     * {@link Object} argument of type parameter {@link V} instead of a {@link String} array.
     *
     * @param sender       the source responsible for execution
     * @param commandLabel the alias of the command used
     * @param argument     the resulting argument
     */
    protected abstract void execute(final K sender, final String commandLabel, final V argument);

    @Override
    public final List<String> tabComplete(final K sender, final String[] args) {
        return args.length <= parameter.getMaximumUsage()
                ? new LinkedList<>(parameter.getSuggestions(sender, args))
                : TabCompletions.emptyList();
    }
}
