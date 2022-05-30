package com.github.commandant.command.parameter.model;

import org.bukkit.util.Vector;

/**
 *
 */
public class VectorParameter extends SenderIndependentParameter<Vector> {
    private static final String NUMBER_REGEX = "\"\\\\d+\\\\.\\\\d+\"";

    public VectorParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    @Override
    public boolean canParse(final String[] args) {
        for (final String arg : args)
            if (!arg.matches(NUMBER_REGEX)) return false;

        return true;
    }

    @Override
    protected Vector parse(final String[] args) {
        return new Vector(parseDouble(args[0]),
                parseDouble(args[1]),
                parseDouble(args[2]));
    }

    private double parseDouble(final String argument) {
        return Double.parseDouble(argument);
    }

    @Override
    public int getSize() {
        return 3;
    }
}
