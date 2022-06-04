package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.ParameterizedCommand;
import org.bukkit.command.CommandSender;

/**
 * A parameter abstraction, with all abilities of {@link AbstractParameter}, for assuming independence of a
 * {@link CommandSender}.
 *
 * @param <V> the type of the resulting {@link Object} parsed from arguments
 */
public abstract class SenderIndependentParameter<V> extends AbstractParameter<CommandSender, V> {
    protected SenderIndependentParameter(final String... usageLabels) {
        super(usageLabels);
    }

    @Override
    public final V parse(final String[] args, final CommandSender sender) {
        return parse(args);
    }

    /**
     * Serves the same functionality as {@link Parameter#parse(String[], CommandSender)} but without a specific
     * {@link CommandSender} to create or retrieve an {@link Object} of type parameter {@link V}.
     *
     * @param args the arguments passed from a {@link ParameterizedCommand}
     * @return the parsed result
     */
    protected abstract V parse(String[] args);
}
