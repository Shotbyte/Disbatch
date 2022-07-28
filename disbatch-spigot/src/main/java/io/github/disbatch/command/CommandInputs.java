package io.github.disbatch.command;

/**
 *
 */
public final class CommandInputs {
    private static final CommandInput EMPTY = new EmptyCommandInput();

    private CommandInputs() {
        throw new AssertionError();
    }

    /**
     * @return
     */
    public static CommandInput empty() {
        return EMPTY;
    }
}
