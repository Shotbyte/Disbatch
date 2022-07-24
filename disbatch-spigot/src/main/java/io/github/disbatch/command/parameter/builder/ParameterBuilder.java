package io.github.disbatch.command.parameter.builder;

import com.google.common.collect.Lists;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.ParameterizedCommand;
import io.github.disbatch.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @param <K>
 * @param <V>
 * @see ParameterBuilder#of(ParameterizedCommand)
 */
public final class ParameterBuilder<K extends CommandSender, V> {
    private ParameterPredicate predicate;
    private ParameterParser<K, V> parser;
    private Suggester<K> suggester = Suggesters.emptySuggester();
    private String[] usageLabels;
    private int minUsage = 1;
    private int maxUsage = Integer.MAX_VALUE;

    /**
     * @param ignored
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K extends CommandSender, V> ParameterBuilder<K, V> of(final ParameterizedCommand<K, V> ignored) {
        return new ParameterBuilder<>();
    }

    public ParameterBuilder<K, V> predicate(final ParameterPredicate predicate) {
        this.predicate = predicate;
        return this;
    }

    public ParameterBuilder<K, V> parser(final ParameterParser<K, V> parser) {
        this.parser = parser;
        return this;
    }

    public ParameterBuilder<K, V> suggester(final Suggester<K> suggester) {
        this.suggester = suggester;
        return this;
    }

    public ParameterBuilder<K, V> minimumUsage(final int minUsage) {
        this.minUsage = minUsage;
        return this;
    }

    public ParameterBuilder<K, V> maximumUsage(final int maxUsage) {
        this.maxUsage = maxUsage;
        return this;
    }

    public ParameterBuilder<K, V> usageLabels(final String... usageLabels) {
        this.usageLabels = usageLabels;
        return this;
    }

    public Parameter<K, V> build() {
        return new BuiltParameter(predicate, parser, suggester, Lists.newArrayList(usageLabels), minUsage, maxUsage);
    }

    private class BuiltParameter implements Parameter<K, V> {
        private final ParameterPredicate predicate;
        private final ParameterParser<K, V> parser;
        private final Suggester<K> suggester;
        private final Collection<String> usageLabels;
        private final int minUsage;
        private final int maxUsage;

        private BuiltParameter(final @NotNull ParameterPredicate predicate, final @NotNull ParameterParser<K, V> parser, final @NotNull Suggester<K> suggester, final Collection<String> usageLabels, final int minUsage, final int maxUsage) {
            this.predicate = predicate;
            this.parser = parser;
            this.suggester = suggester;
            this.usageLabels = usageLabels;
            this.minUsage = minUsage;
            this.maxUsage = maxUsage;
        }

        @Override
        public boolean canParse(final CommandInput input) {
            return predicate.canParse(input);
        }

        @Override
        public V parse(final CommandInput input, final K sender) {
            return parser.parse(input, sender);
        }

        @Override
        public Collection<String> getUsageLabels() {
            return usageLabels;
        }

        @Override
        public Collection<String> getSuggestions(final K sender, final CommandInput input) {
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
    }
}
