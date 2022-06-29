package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import org.bukkit.command.CommandSender;
import org.bukkit.util.Vector;

/**
 * Creates a {@link Vector} based on parsable, passed arguments.
 *
 * @param <T> {@inheritDoc}
 */
public final class VectorParameter<T extends CommandSender> extends NumericalParameter<T, Vector> {
    public VectorParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    public VectorParameter(final String xLabel, final String yLabel, final String zLabel, final Suggester<T> suggester) {
        super(suggester, xLabel, yLabel, zLabel);
    }

    @Override
    public Vector parse(final String[] boundedArgs, final T sender) {
        return new Vector(parseNumber(boundedArgs[0]), parseNumber(boundedArgs[1]), parseNumber(boundedArgs[2]));
    }
}
