package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.ParameterUsage;
import org.bukkit.command.CommandSender;

/**
 * A parameter abstraction that is not only able to cache its usage labels but also has its minimum and maximum usages
 * deemed by the length of those labels by default.
 *
 * @param <K> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public abstract class AbstractParameter<K extends CommandSender, V> implements Parameter<K, V> {
    private final String[] usageLabels;

    protected AbstractParameter(final String... usageLabels) {
        this.usageLabels = usageLabels;
    }

    @Override
    public String createUsageMessage(final String commandLabel, final ParameterUsage usage) {
        return usage.toMessage(commandLabel, usageLabels);
    }

    @Override
    public int getMinimumUsage() {
        return usageLabels.length;
    }

    @Override
    public int getMaximumUsage() {
        return usageLabels.length;
    }
}
