package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.ArgumentQueue;
import com.github.commandant.command.parameter.ArgumentSelection;
import org.bukkit.util.Vector;

/**
 *
 */
public class VectorParameter extends SenderIndependentParameter<Vector> {
    private static final String NUMBER_REGEX = "\"\\\\d+\\\\.\\\\d+\"";

    public VectorParameter(final String label) {
        super(label);
    }

    @Override
    public boolean canParse(final ArgumentSelection selection) {
        for (final String argument : selection)
            if (!argument.matches(NUMBER_REGEX)) return false;

        return true;
    }

    @Override
    protected Vector parse(final ArgumentQueue queue) {
        return new Vector(parseDouble(queue.nextArgument()),
                parseDouble(queue.nextArgument()),
                parseDouble(queue.nextArgument()));
    }

    private double parseDouble(final String argument) {
        return Double.parseDouble(argument);
    }

    @Override
    public int getSize() {
        return 3;
    }
}
