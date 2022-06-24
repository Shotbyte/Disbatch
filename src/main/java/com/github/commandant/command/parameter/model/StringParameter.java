package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import com.github.commandant.command.parameter.builder.Suggesters;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Forms a {@link String} from all passed arguments, joined via single whitespace, within a nearly-infinite usage
 * span by default.
 */
public final class StringParameter<T extends CommandSender> extends AbstractParameter<T, String> {
    private final int minUsage;
    private final int maxUsage;

    private StringParameter(final int minUsage, final int maxUsage, @NotNull final Suggester<T> suggester, @NotNull final String[] usageLabels) {
        super(suggester, usageLabels);
        this.minUsage = minUsage;
        this.maxUsage = maxUsage;
    }

    @Override
    public boolean canParse(final String[] args) {
        for (final String arg : args)
            if (arg.isEmpty()) return false;

        return true;
    }

    @Override
    public String parse(final String[] args, final T sender) {
        return String.join(" ", args);
    }

    @Override
    public int getMinimumUsage() {
        return minUsage;
    }

    @Override
    public int getMaximumUsage() {
        return maxUsage;
    }

    /**
     *
     */
    public static final class Builder<T extends CommandSender> {
        private Suggester<T> suggester = Suggesters.emptySuggester();
        private String[] usageLabels;
        private int minUsage = 1;
        private int maxUsage = Integer.MAX_VALUE;

        public Builder<T> usageLabels(final String... usageLabels) {
            this.usageLabels = usageLabels;
            return this;
        }

        public Builder<T> suggester(final Suggester<T> suggester) {
            this.suggester = suggester;
            return this;
        }

        public Builder<T> minimumUsage(final int minUsage) {
            this.minUsage = minUsage;
            return this;
        }

        public Builder<T> maximumUsage(final int maxUsage) {
            this.maxUsage = maxUsage;
            return this;
        }

        public StringParameter<T> build() {
            return new StringParameter<>(minUsage, maxUsage, suggester, usageLabels);
        }
    }
}
