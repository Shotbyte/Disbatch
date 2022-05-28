package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;

/**
 * @param <K>
 * @param <V>
 */
public abstract class AbstractParameter<K extends CommandSender, V> implements Parameter<K, V> {
    private final String label;

    protected AbstractParameter(final String label) {
        this.label = label;
    }

    @Override
    public final String getLabel() {
        return label;
    }
}
