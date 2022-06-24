package com.github.commandant.command.parameter;

import com.github.commandant.command.parameter.model.Parameter;

/**
 * Thrown when any bounds of a {@link Parameter} cannot be used by a {@link ParameterizedCommand}.
 */
public class ParameterOutOfBoundsException extends RuntimeException {

    /**
     * Constructs a new {@link ParameterOutOfBoundsException}.
     *
     * @param message {@inheritDoc} the message to describe the conditions of the exception being thrown
     */
    public ParameterOutOfBoundsException(final String message) {
        super(message);
    }
}
