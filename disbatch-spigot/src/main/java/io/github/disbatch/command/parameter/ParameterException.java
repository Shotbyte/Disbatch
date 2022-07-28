package io.github.disbatch.command.parameter;

/**
 *
 */
public class ParameterException extends RuntimeException {

    /**
     * @param message the message to describe the conditions from the exception being thrown
     */
    public ParameterException(final String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ParameterException(final Exception cause) {
        super(cause);
    }
}
