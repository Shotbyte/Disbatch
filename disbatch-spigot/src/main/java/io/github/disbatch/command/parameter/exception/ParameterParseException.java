package io.github.disbatch.command.parameter.exception;

import io.github.disbatch.command.parameter.ParameterizedCommand;
import io.github.disbatch.command.parameter.model.Parameter;

/**
 * Thrown when any arguments from a {@link Parameter} cannot be parsed within a {@link ParameterizedCommand}.
 */
public class ParameterParseException extends ParameterException {

    /**
     * Constructs a new {@link ParameterParseException}.
     */
    public ParameterParseException(final String message) {
        super(message);
    }
}
