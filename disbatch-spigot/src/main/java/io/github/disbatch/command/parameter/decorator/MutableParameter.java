package io.github.disbatch.command.parameter.decorator;

import com.google.common.collect.ImmutableList;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.exception.ParameterException;
import io.github.disbatch.command.parameter.exception.ParameterParseException;
import io.github.disbatch.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * @param <S> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public final class MutableParameter<S extends CommandSender, V> implements Parameter<S, V> {
    private static final Parameter<?, ?> EMPTY_PARAMETER = new EmptyParameter();

    @SuppressWarnings("unchecked")
    private Parameter<S, V> underlyingParameter = (Parameter<S, V>) EMPTY_PARAMETER;

    /**
     * @param underlyingParameter
     */
    public void setUnderlyingParameter(final @NotNull Parameter<S, V> underlyingParameter) {
        this.underlyingParameter = underlyingParameter;
    }

    @Override
    public Optional<V> parse(final CommandInput input, final S sender) {
        return underlyingParameter.parse(input, sender);
    }

    @Override
    public Collection<String> getUsageLabels() {
        return underlyingParameter.getUsageLabels();
    }

    @Override
    public Collection<String> getSuggestions(final S sender, final CommandInput input) {
        return underlyingParameter.getSuggestions(sender, input);
    }

    @Override
    public int getMinimumUsage() {
        return underlyingParameter.getMinimumUsage();
    }

    @Override
    public int getMaximumUsage() {
        return underlyingParameter.getMaximumUsage();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("underlyingParameter=" + underlyingParameter)
                .toString();
    }

    private static class EmptyParameter implements Parameter<CommandSender, Void> {
        @Override
        public Optional<Void> parse(final CommandInput input, final CommandSender sender) {
            throw newParameterException();
        }

        private ParameterException newParameterException() {
            return new ParameterParseException("No actual underlying parameter has been set");
        }

        @Override
        public Collection<String> getUsageLabels() {
            return ImmutableList.of();
        }

        @Override
        public int getMinimumUsage() {
            return 1;
        }

        @Override
        public int getMaximumUsage() {
            return 1;
        }
    }
}
