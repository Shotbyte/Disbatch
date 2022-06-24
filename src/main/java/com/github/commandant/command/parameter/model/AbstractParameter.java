package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import com.github.commandant.command.parameter.builder.Suggesters;
import com.google.common.collect.Lists;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * A parameter abstraction that is not only able to cache its usage labels but also has {@link #getMinimumUsage()} and
 * {@link #getMaximumUsage()} deemed by the length of those labels by default.
 *
 * @param <K> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public abstract class AbstractParameter<K extends CommandSender, V> implements Parameter<K, V> {
    private final Suggester<K> suggester;
    private final Collection<String> usageLabels;

    protected AbstractParameter(final String... usageLabels) {
        this(Suggesters.emptySuggester(), usageLabels);
    }

    protected AbstractParameter(@NotNull final Suggester<K> suggester, @NotNull final String... usageLabels) {
        this.suggester = suggester;
        this.usageLabels = Lists.newArrayList(usageLabels);
    }

    @Override
    public Collection<String> getUsageLabels() {
        return usageLabels;
    }

    @Override
    public Collection<String> getSuggestions(final K sender, final String[] boundedArgs) {
        return suggester.getSuggestions(sender, boundedArgs);
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
