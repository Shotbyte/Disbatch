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
 * @param <S> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public abstract class AbstractParameter<S extends CommandSender, V> implements Parameter<S, V> {
    private final Collection<String> usageLabels;
    private Suggester<S> suggester = Suggesters.empty();

    /**
     * @param usageLabels
     */
    protected AbstractParameter(final @NotNull String... usageLabels) {
        this.usageLabels = Lists.newArrayList(usageLabels);
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
        return usageLabels.size();
    }

    @Override
    public int getMaximumUsage() {
        return usageLabels.size();
    }

    /**
     * @param suggester
     * @param <SS>
     * @param <VV>
     * @return
     */
    @SuppressWarnings("unchecked")
    public final <SS extends CommandSender, VV> Parameter<SS, VV> withSuggester(final @NotNull Suggester<SS> suggester) {
        this.suggester = (Suggester<S>) suggester;
        return (Parameter<SS, VV>) this;
    }
}
