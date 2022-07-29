package io.github.disbatch.command.parameter.builder;

import com.google.common.collect.Lists;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.ParameterizedCommand;
import io.github.disbatch.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * @param <S>
 * @param <V>
 * @see ParameterBuilder#of(ParameterizedCommand)
 */
public final class ParameterBuilder<S extends CommandSender, V> {
    private ParameterParser<S, V> parser;
    private Suggester<S> suggester = Suggesters.empty();
    private String[] usageLabels;
    private int minUsage = 1;
    private int maxUsage = Integer.MAX_VALUE;

    /**
     * @param ignored
     * @param <S>
     * @param <V>
     * @return
     */
    public static <S extends CommandSender, V> ParameterBuilder<S, V> of(final ParameterizedCommand<S, V> ignored) {
        return new ParameterBuilder<>();
    }

    public ParameterBuilder<S, V> parser(final ParameterParser<S, V> parser) {
        this.parser = parser;
        return this;
    }

    public ParameterBuilder<S, V> suggester(final Suggester<S> suggester) {
        this.suggester = suggester;
        return this;
    }

    public ParameterBuilder<S, V> minimumUsage(final int minUsage) {
        this.minUsage = minUsage;
        return this;
    }

    public ParameterBuilder<S, V> maximumUsage(final int maxUsage) {
        this.maxUsage = maxUsage;
        return this;
    }

    public ParameterBuilder<S, V> usageLabels(final String... usageLabels) {
        this.usageLabels = usageLabels;
        return this;
    }

    public Parameter<S, V> build() {
        return new BuiltParameter(parser, suggester, Lists.newArrayList(usageLabels), minUsage, maxUsage);
    }

    private class BuiltParameter implements Parameter<S, V> {
        private final ParameterParser<S, V> parser;
        private final Suggester<S> suggester;
        private final Collection<String> usageLabels;
        private final int minUsage;
        private final int maxUsage;

        private BuiltParameter(final @NotNull ParameterParser<S, V> parser, final @NotNull Suggester<S> suggester, final Collection<String> usageLabels, final int minUsage, final int maxUsage) {
            this.parser = parser;
            this.suggester = suggester;
            this.usageLabels = usageLabels;
            this.minUsage = minUsage;
            this.maxUsage = maxUsage;
        }

        @Override
        public Optional<V> parse(final CommandInput input, final S sender) {
            return parser.parse(input, sender);
        }

        @Override
        public Collection<String> getUsageLabels() {
            return usageLabels;
        }

        @Override
        public Collection<String> getSuggestions(final S sender, final CommandInput input) {
            return suggester.getSuggestions(sender, input);
        }

        @Override
        public int getMinimumUsage() {
            return minUsage;
        }

        @Override
        public int getMaximumUsage() {
            return maxUsage;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                    .add("parser=" + parser)
                    .add("suggester=" + suggester)
                    .add("usageLabels=" + usageLabels)
                    .add("minUsage=" + minUsage)
                    .add("maxUsage=" + maxUsage)
                    .toString();
        }
    }
}
