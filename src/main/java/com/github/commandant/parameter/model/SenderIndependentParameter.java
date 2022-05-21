package com.github.commandant.parameter.model;

import com.github.commandant.parameter.ArgumentQueue;
import org.bukkit.command.CommandSender;

/**
 * @param <V>
 */
public abstract class SenderIndependentParameter<V> extends AbstractParameter<CommandSender, V> {
    protected SenderIndependentParameter(final String label) {
        super(label);
    }

    @Override
    public final V parse(final ArgumentQueue queue, final CommandSender sender) {
        return parse(queue);
    }

    protected abstract V parse(ArgumentQueue queue);
}
