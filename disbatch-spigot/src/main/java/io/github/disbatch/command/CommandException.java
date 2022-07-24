package io.github.disbatch.command;

/**
 * @apiNote Not to be confused with {@link org.bukkit.command.CommandException}.
 */
public class CommandException extends RuntimeException {

    /**
     * @param message
     */
    public CommandException(final String message) {
        super(message);
    }
}
