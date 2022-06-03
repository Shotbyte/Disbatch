package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;

/**
 * @param <K>
 * @param <V>
 */
public abstract class NumericParameter<K extends CommandSender, V> extends AbstractParameter<K, V> {
    private static final String NUMBER_REGEX = "\"\\\\d+\\\\.\\\\d+\"";

    protected NumericParameter(final String... labels) {
        super(labels);
    }

    @Override
    public final boolean canParse(final String[] args) {
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
