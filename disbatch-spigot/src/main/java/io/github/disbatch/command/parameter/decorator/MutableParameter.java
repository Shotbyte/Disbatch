package io.github.disbatch.command.parameter.decorator;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.builder.TabCompletions;
import io.github.disbatch.command.parameter.ParameterException;
import io.github.disbatch.command.parameter.ParameterParseException;
import io.github.disbatch.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * @param <K> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public final class MutableParameter<K extends CommandSender, V> implements Parameter<K, V> {
    private static final Parameter<?, ?> EMPTY_PARAMETER = new EmptyParameter();

    @SuppressWarnings("unchecked")
    private Parameter<K, V> underlyingParameter = (Parameter<K, V>) EMPTY_PARAMETER;

    /**
     * @param underlyingParameter
     */
    public void setUnderlyingParameter(final @NotNull Parameter<K, V> underlyingParameter) {
        this.underlyingParameter = underlyingParameter;
    }

    @Override
    public boolean canParse(final CommandInput input) {
        return underlyingParameter.canParse(input);
    }

    @Override
    public V parse(final CommandInput input, final K sender) {
        return underlyingParameter.parse(input, sender);
    }

    @Override
    public Collection<String> getUsageLabels() {
        return underlyingParameter.getUsageLabels();
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

    private static class EmptyParameter implements Parameter<CommandSender, Object> {
        @Override
        public boolean canParse(final CommandInput input) {
            throw newParameterException();
        }

        @Override
        public Object parse(final CommandInput input, final CommandSender sender) {
            throw newParameterException();
        }

        private ParameterException newParameterException() {
            return new ParameterParseException("No actual underlying parameter has been set");
        }

        @Override
        public Collection<String> getUsageLabels() {
            return TabCompletions.emptyList();
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
