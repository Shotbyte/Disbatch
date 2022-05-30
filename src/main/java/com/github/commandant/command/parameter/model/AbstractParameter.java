package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;

/**
 * @param <K>
 * @param <V>
 */
public abstract class AbstractParameter<K extends CommandSender, V> implements Parameter<K, V> {
    private final String[] labels;

    protected AbstractParameter(final String... labels) {
        this.labels = labels;
    }

    @Override
    public final String[] getLabels() {
        return labels;
    }
}
