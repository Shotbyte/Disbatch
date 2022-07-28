package io.github.disbatch.command.parameter.model;

import com.google.common.base.Strings;
import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Forms a {@link String} from all passed arguments, joined via single whitespace.
 *
 * @param <S> {@inheritDoc}
 */
public final class StringParameter<S extends CommandSender> extends AbstractParameter<S, String> {
    private final int maxUsage;

    private StringParameter(final int maxUsage, final @NotNull String usageLabel) {
        super(usageLabel);
        this.maxUsage = maxUsage;
    }

    /**
     * @param usageLabel
     * @param <S>
     * @return
     */
    public static <S extends CommandSender> StringParameter<S> ofInfiniteUsage(final String usageLabel) {
        return StringParameter.<S>builder()
                .usageLabel(usageLabel)
                .maximumUsage(Integer.MAX_VALUE)
                .build();
    }

    /**
     * Creates a {@link StringParameter.Builder}.
     *
     * @param <S> any type extending {@link CommandSender} required to parse arguments
     * @return the created builder
     */
    public static <S extends CommandSender> Builder<S> builder() {
        return new Builder<>();
    }

    @Override
    public Optional<String> parse(final CommandInput input, final S sender) {
        return Optional.ofNullable(Strings.emptyToNull(input.getArgumentLine()));
    }

    @Override
    public int getMaximumUsage() {
        return maxUsage;
    }

    /**
     * Serves as a flexible solution for creating a new {@link StringParameter}.
     *
     * @param <S> any type extending {@link CommandSender} required to parse arguments
     */
    public static final class Builder<S extends CommandSender> {
        private String usageLabel;
        private int maxUsage;

        private Builder() {
        }

        public Builder<S> usageLabel(final String usageLabel) {
            this.usageLabel = usageLabel;
            return this;
        }

        public Builder<S> maximumUsage(final int maxUsage) {
            this.maxUsage = maxUsage;
            return this;
        }

        /**
         * Creates a new {@link StringParameter}.
         *
         * @return the created string parameter
         */
        public StringParameter<S> build() {
            return new StringParameter<>(maxUsage, usageLabel);
        }
    }
}
