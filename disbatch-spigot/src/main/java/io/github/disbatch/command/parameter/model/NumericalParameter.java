package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.ParameterizedCommand;
import io.github.disbatch.command.parameter.builder.Suggester;
import org.bukkit.command.CommandSender;

/**
 * Holds the functionalities necessary to create or retrieve an {@link Object} relating to numeric use-cases based on
 * parsable, passed arguments.
 *
 * @param <K> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public abstract class NumericalParameter<K extends CommandSender, V> extends AbstractParameter<K, V> {
    private static final String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";

    protected NumericalParameter(final String... usageLabels) {
        super(usageLabels);
    }

    protected NumericalParameter(final Suggester<K> suggester, final String... usageLabels) {
        super(suggester, usageLabels);
    }

    /**
     * Determines if all arguments can be parsed to a number by default.
     *
     * @param input the arguments passed from a {@link ParameterizedCommand}
     * @return if the passed arguments can be parsed
     */
    @Override
    public boolean canParse(final CommandInput input) {
        for (final String argument : input.getArguments())
            if (!canParseNumber(argument)) return false;

        return true;
    }

    /**
     * Determines if the given argument can be parsed to a number.
     *
     * @param arg the given argument
     * @return if the given argument can be parsed
     */
    protected final boolean canParseNumber(final String arg) {
        return arg.matches(NUMBER_REGEX);
    }

    /**
     * Parses the given argument to a number.
     *
     * @param arg the given argument
     * @return a parsed number in the form from a {@code double}.
     */
    protected final double parseNumber(final String arg) {
        final String decimal = ".";
        return Double.parseDouble(arg.contains(decimal) ? arg : arg + decimal);
    }
}
