package com.github.commandant.command.parameter;

import com.github.commandant.command.Command;
import com.github.commandant.command.parameter.model.Parameter;
import com.google.common.base.Strings;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * Introduces the concept of transforming a specific set of arguments of a compatible length into a usable
 * {@link Object} with very minimal heavy-lifting during execution.
 *
 * @param <K> {@inheritDoc}
 * @param <V> the type of the resulting {@link Object} parsed from a set of arguments
 * @see Parameter
 * @see ParameterUsage
 */
public abstract class ParameterizedCommand<K extends CommandSender, V> implements Command<K> {
    private final Parameter<? super K, V> parameter;
    private final ParameterUsage usage;
    private final String invalidArgumentsMessage;

    protected ParameterizedCommand(final Parameter<? super K, V> parameter, final ParameterUsage usage) {
        this(parameter, usage, null);
    }

    protected ParameterizedCommand(final Parameter<? super K, V> parameter, final ParameterUsage usage, final String invalidArgumentsMessage) {
        this.parameter = parameter;
        this.usage = usage;
        this.invalidArgumentsMessage = invalidArgumentsMessage;
    }

    @Override
    public final void execute(final K sender, final String commandLabel, final String[] args) {
        final String[] labels = parameter.getUsageLabels();
        final boolean isCompatibleLength = args.length >= labels.length && args.length <= parameter.getUsageSpan();

        if (isCompatibleLength && parameter.canParse(args))
            execute(sender, commandLabel, parameter.parse(args, sender));
        else if (!isCompatibleLength || Strings.isNullOrEmpty(invalidArgumentsMessage))
            sender.sendMessage(usage.toMessage(commandLabel, labels));
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
        return args.length <= parameter.getUsageSpan()
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