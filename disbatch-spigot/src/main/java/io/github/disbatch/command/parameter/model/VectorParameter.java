package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;
import org.bukkit.util.Vector;

import java.util.Optional;

/**
 * Creates a {@link Vector} based on parsable, passed arguments.
 *
 * @param <S> {@inheritDoc}
 */
public final class VectorParameter<S extends CommandSender> extends NumericalParameter<S, Vector> {

    /**
     * @param xLabel
     * @param yLabel
     * @param zLabel
     */
    public VectorParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    @Override
    public Optional<Vector> parse(final CommandInput input, final S sender) {
        return numericOptional(input, new Vector(parseNumber(input.getArgument(0)),
                parseNumber(input.getArgument(1)),
                parseNumber(input.getArgument(2))));
    }
}
