package io.github.disbatch.command.parameter.model;

import com.google.common.collect.Lists;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.builder.Suggester;
import io.github.disbatch.command.parameter.builder.Suggesters;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * A parameter abstraction that is not only able to cache its usage labels but also has {@link #getMinimumUsage()} and
 * {@link #getMaximumUsage()} deemed by the length from those labels by default.
 *
 * @param <K> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public abstract class AbstractParameter<K extends CommandSender, V> implements Parameter<K, V> {
    private final Suggester<K> suggester;
    private final Collection<String> usageLabels;

    /**
     * @param usageLabels
     */
    protected AbstractParameter(final String... usageLabels) {
        this(Suggesters.emptySuggester(), usageLabels);
    }

    /**
     * @param suggester
     * @param usageLabels
     */
    protected AbstractParameter(final @NotNull Suggester<K> suggester, final @NotNull String... usageLabels) {
        this.suggester = suggester;
        this.usageLabels = Lists.newArrayList(usageLabels);
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
        return usageLabels.size();
    }

    @Override
    public int getMaximumUsage() {
        return usageLabels.size();
    }
}
