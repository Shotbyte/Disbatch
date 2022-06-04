package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;

/**
 * A parameter abstraction that is not only able to cache its usage labels but also has its usage span deemed by the
 * number of those labels by default.
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
    public final String[] getUsageLabels() {
        return usageLabels;
    }

    @Override
    public int getUsageSpan() {
        return usageLabels.length;
    }
}
