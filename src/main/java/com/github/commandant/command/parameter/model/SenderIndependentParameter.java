package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;

/**
 * @param <V>
 */
public abstract class SenderIndependentParameter<V> extends AbstractParameter<CommandSender, V> {
    protected SenderIndependentParameter(final String... labels) {
        super(labels);
    }

    @Override
    public final V parse(final String[] args, final CommandSender sender) {
        return parse(args);
    }

    protected abstract V parse(String[] args);
}
