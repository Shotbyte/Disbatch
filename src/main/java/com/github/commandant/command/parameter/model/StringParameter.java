package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import com.github.commandant.command.parameter.builder.Suggesters;
import org.bukkit.command.CommandSender;

/**
 * Forms a {@link String} from all passed arguments, joined via single whitespace, within a nearly-infinite usage
 * span by default.
 */
public final class StringParameter<T extends CommandSender> extends StringParsableParameter<T, String> {
    private final int minUsage;
    private final int maxUsage;

    private StringParameter(final int minUsage, final int maxUsage, final Suggester<T> suggester, final String[] usageLabels) {
        super(suggester, usageLabels);

        this.minUsage = minUsage == 0 ? usageLabels.length : minUsage;
        this.maxUsage = maxUsage == 0 ? usageLabels.length : maxUsage;
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
        private int minUsage;
        private int maxUsage;

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
