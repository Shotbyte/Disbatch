package io.github.disbatch.command;

public class CommandExecutionException extends CommandException {
    /**
     * @param message
     */
    public CommandExecutionException(final String message) {
        super(message);
    }
}
