package com.github.commandant.command.parameter;

import com.github.commandant.command.Command;
import com.github.commandant.command.parameter.model.Parameter;
import com.google.common.base.Strings;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * Introduces the concept of effortlessly transforming a specific set of arguments of a compatible length into a usable
 * {@link Object} during execution.
 *
 * @param <K> {@inheritDoc}
 * @param <V> the type of the resulting {@link Object} parsed from a set of arguments
 * @see ParameterizedCommand#execute(CommandSender, String, Object)
 * @see ParameterizedCommand#tabComplete(CommandSender, String)
 * @see Parameter
 * @see ParameterUsage
 */
public abstract class ParameterizedCommand<K extends CommandSender, V> implements Command<K> {
    private final Parameter<? super K, V> parameter;
    private final ParameterUsage usage;
    private final String invalidArgumentsMessage;

    /**
     * Constructs a new {@link ParameterizedCommand} without an invalid arguments message.
     *
     * @param parameter the parameter to use for {@link Object} argument creation
     * @param usage     the usage to use for creating usage messages when necessary
     * @throws ParameterBoundsException if any bounds of the passed parameter cannot be used
     */
    protected ParameterizedCommand(final Parameter<? super K, V> parameter, final ParameterUsage usage) {
        this(parameter, usage, null);
    }

    /**
     * Constructs a new {@link ParameterizedCommand}.
     *
     * @param parameter               the parameter to use for {@link Object} argument creation
     * @param usage                   the usage to use for creating usage messages when necessary
     * @param invalidArgumentsMessage the message that should be sent to the {@link CommandSender} for passing invalid
     *                                arguments (the normal usage message will be sent instead if null or empty)
     * @throws ParameterBoundsException if any bounds of the passed parameter cannot be used
     */
    protected ParameterizedCommand(final Parameter<? super K, V> parameter, final ParameterUsage usage, final String invalidArgumentsMessage) {
        validateBounds(parameter);

        this.parameter = parameter;
        this.usage = usage;
        this.invalidArgumentsMessage = invalidArgumentsMessage;
    }

    private void validateBounds(final Parameter<?, ?> parameter) {
        final int minUsage = parameter.getMinimumUsage();
        final int maxUsage = parameter.getMaximumUsage();

        if (minUsage <= 0) throw new ParameterBoundsException("Minimum usage must be greater than 0");
        if (maxUsage <= 0) throw new ParameterBoundsException("Maximum usage must be greater than 0");
        if (minUsage > maxUsage) throw new ParameterBoundsException("Minimum usage cannot exceed maximum usage");
    }

    @Override
    public final void execute(final K sender, final String commandLabel, final String[] args) {
        final boolean isCompatibleLength = args.length >= parameter.getMinimumUsage() && args.length <= parameter.getMaximumUsage();

        if (isCompatibleLength && parameter.canParse(args))
            execute(sender, commandLabel, parameter.parse(args, sender));
        else if (!isCompatibleLength || Strings.isNullOrEmpty(invalidArgumentsMessage))
            sender.sendMessage(parameter.createUsageMessage(commandLabel, usage));
        else
            sender.sendMessage(invalidArgumentsMessage);
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
    public List<String> tabComplete(final K sender, final String[] args) {
        return args.length <= parameter.getMaximumUsage()
                ? tabComplete(sender, args[args.length - 1])
                : Collections.emptyList();
    }

    /**
     * Serves the same functionality as {@link Command#tabComplete(CommandSender, String[])} but with a single
     * {@link String} argument instead of a {@link String} array.
     *
     * @param sender   the source responsible for initiating a tab completion
     * @param argument the {@link String} argument at the end of the input within the current parameter's usage span
     * @return a list of tab-completions for the specified arguments, which may be empty or immutable
     */
    protected List<String> tabComplete(final K sender, final String argument) {
        return Collections.emptyList();
    }
}