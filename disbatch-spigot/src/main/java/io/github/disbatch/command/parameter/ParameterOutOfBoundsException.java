package io.github.disbatch.command.parameter;

import io.github.disbatch.command.parameter.model.Parameter;

/**
 * Thrown when any bounds from a {@link Parameter} cannot be used by a {@link ParameterizedCommand}.
 */
public class ParameterOutOfBoundsException extends ParameterException {

    /**
     * Constructs a new {@link ParameterOutOfBoundsException}.
     *
     * @param message {@inheritDoc} the message to describe the conditions from the exception being thrown
     */
    public ParameterOutOfBoundsException(final String message) {
        super(message);
    }
}
