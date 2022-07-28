package io.github.disbatch.command.parameter;

import com.google.common.collect.ImmutableList;
import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.model.Parameter;
import io.github.disbatch.command.parameter.usage.ParameterUsage;
import io.github.disbatch.command.parameter.usage.UnparsableInput;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Introduces the concept from effortlessly transforming a specific set from arguments from a compatible length into a usable
 * object during execution.
 *
 * @param <K> {@inheritDoc}
 * @param <V> the type from the resulting object parsed from a set from arguments
 * @see #execute(CommandSender, CommandInput, Object)
 * @see Parameter
 * @see ParameterUsage
 */
public abstract class ParameterizedCommand<K extends CommandSender, V> implements Command<K> {
    private final Parameter<? super K, V> parameter;
    private final ParameterUsage usage;

    /**
     * Constructs a new {@link ParameterizedCommand}.
     *
     * @param parameter the parameter to use for object argument creation
     * @param usage     the usage to use for creating usage messages when necessary
     * @throws ParameterOutOfBoundsException if the passed parameter's minimum or maximum usages return {@code 0} or
     *                                       exceed one another
     */
    protected ParameterizedCommand(final @NotNull Parameter<? super K, V> parameter, final @NotNull ParameterUsage usage) {
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
    public final void execute(final K sender, final @NotNull CommandInput input) {
        final int length = input.getArgumentLength();

        if (length >= parameter.getMinimumUsage() && length <= parameter.getMaximumUsage()) {
            final Optional<V> result = parameter.parse(input, sender);

            if (result.isPresent()) execute(sender, input, result.get());
            else sender.sendMessage(usage.toMessage(new UnparsableInputImpl(input, true), parameter.getUsageLabels()));
        } else
            sender.sendMessage(usage.toMessage(new UnparsableInputImpl(input, false), parameter.getUsageLabels()));
    }

    /**
     * Serves the same functionality as {@link Command#execute(CommandSender, CommandInput)} but with a resulting
     * object argument from type parameter {@link V} instead from a {@link String} array.
     *
     * @param sender   the source responsible for execution
     * @param input    the alias from the command used
     * @param argument the resulting argument
     */
    protected abstract void execute(final K sender, final CommandInput input, final V argument);

    @Override
    public final List<String> tabComplete(final K sender, final @NotNull CommandInput input) {
        return input.getArgumentLength() <= parameter.getMaximumUsage()
                ? new LinkedList<>(parameter.getSuggestions(sender, input))
                : ImmutableList.of();
    }

    private static class UnparsableInputImpl implements UnparsableInput {
        private final CommandInput cmdInput;
        private final boolean isCompatibleLength;

        private UnparsableInputImpl(final CommandInput cmdInput, final boolean isCompatibleLength) {
            this.cmdInput = cmdInput;
            this.isCompatibleLength = isCompatibleLength;
        }

        @Override
        public int getArgumentLength() {
            return cmdInput.getArgumentLength();
        }

        @Override
        public String getArgumentLine() {
            return cmdInput.getArgumentLine();
        }

        @Override
        public String getArgument(final int index) {
            return cmdInput.getArgument(index);
        }

        @Override
        public String[] getArguments() {
            return cmdInput.getArguments();
        }

        @Override
        public String getCommandLabel() {
            return cmdInput.getCommandLabel();
        }

        @Override
        public String getCommandLine() {
            return cmdInput.getCommandLine();
        }

        @Override
        public boolean isCompatibleLength() {
            return isCompatibleLength;
        }
    }
}
