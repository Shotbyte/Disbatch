package com.github.commandant.command.parameter;

import com.github.commandant.command.parameter.model.Parameter;

/**
 * Thrown when any bounds of a {@link Parameter} cannot be used by a {@link ParameterizedCommand}.
 */
public class ParameterBoundsException extends RuntimeException {
    ParameterBoundsException(final String message) {
        super(message);
    }
}
