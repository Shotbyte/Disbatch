package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.builder.Suggester;
import io.github.disbatch.command.parameter.builder.Suggesters;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Forms a {@link String} from all passed arguments, joined via single whitespace.
 *
 * @param <T> {@inheritDoc}
 */
public final class StringParameter<T extends CommandSender> extends StringParsableParameter<T, String> {
    private final int minUsage;
    private final int maxUsage;

    private StringParameter(final int minUsage, final int maxUsage, final @NotNull Suggester<T> suggester, final @NotNull String[] usageLabels) {
        super(suggester, usageLabels);

        this.minUsage = minUsage;
        this.maxUsage = maxUsage;
    }

    /**
     * Creates a {@link StringParameter.Builder}.
     *
     * @param <T> any type extending {@link CommandSender} required to parse arguments
     * @return the created builder
     */
    public static <T extends CommandSender> Builder<T> builder() {
        return new Builder<>();
    }

    @Override
    public String parse(final CommandInput input, final T sender) {
        return input.getArgumentLine();
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
     * Serves as a flexible solution for creating a new {@link StringParameter}.
     *
     * @param <T> any type extending {@link CommandSender} required to parse arguments
     */
    public static final class Builder<T extends CommandSender> {
        private Suggester<T> suggester = Suggesters.emptySuggester();
        private String[] usageLabels;
        private int minUsage;
        private int maxUsage;

        private Builder() {
        }

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

        /**
         * Creates a new {@link StringParameter}.
         *
         * @return the created string parameter
         */
        public StringParameter<T> build() {
            final int minUsage = this.minUsage == 0 ? usageLabels.length : this.minUsage;
            final int maxUsage = this.maxUsage == 0 ? usageLabels.length : this.maxUsage;

            return new StringParameter<>(minUsage, maxUsage, suggester, usageLabels);
        }
    }
}
