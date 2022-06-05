package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;

/**
 * Holds the functionalities necessary to create or retrieve an {@link Object} relating to numeric use-cases based on
 * parsable, passed arguments.
 *
 * @param <K> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public abstract class NumericalParameter<K extends CommandSender, V> extends AbstractParameter<K, V> {
    private static final String NUMBER_REGEX = "\"\\\\d+\\\\.\\\\d+\"";

    protected NumericalParameter(final String... usageLabels) {
        super(usageLabels);
    }

    @Override
    public boolean canParse(final String[] args) {
        for (final String arg : args)
            if (!arg.matches(NUMBER_REGEX)) return false;

        return true;
    }

    /**
     * @param argument
     * @return
     */
    protected final double parseNumber(final String argument) {
        return Double.parseDouble(argument.contains(".") ? argument : argument + ".");
    }
}
