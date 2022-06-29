package com.github.commandant.command.parameter.decorator;

import com.github.commandant.command.parameter.ParameterException;
import com.github.commandant.command.parameter.model.Parameter;
import lombok.experimental.Delegate;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * @param <K> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public final class MutableParameter<K extends CommandSender, V> implements Parameter<K, V> {
    private static final Parameter<?, ?> EMPTY_PARAMETER = new EmptyParameter();

    @SuppressWarnings("unchecked")
    @Delegate
    private Parameter<K, V> underlyingParameter = (Parameter<K, V>) EMPTY_PARAMETER;

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("underlyingParameter=" + underlyingParameter)
                .toString();
    }

    /**
     * @param underlyingParameter
     */
    public void setUnderlyingParameter(@NotNull final Parameter<K, V> underlyingParameter) {
        this.underlyingParameter = underlyingParameter;
    }

    private static class EmptyParameter implements Parameter<CommandSender, Object> {
        @Override
        public boolean canParse(final String[] args) {
            throw newParameterException();
        }

        @Override
        public Optional<Object> parse(final String[] boundedArgs, final CommandSender sender) {
            throw newParameterException();
        }

        @Override
        public Collection<String> getUsageLabels() {
            throw newParameterException();
        }

        private ParameterException newParameterException() {
            return new ParameterException("No actual underlying parameter has been set");
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
