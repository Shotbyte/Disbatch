package io.github.disbatch.command;

/**
 * Holds various components of the command line used to execute a specific {@link Command}.
 */
public interface CommandInput {

    /**
     * Retrieves the amount of passed arguments.
     *
     * @return the argument amount
     */
    int getArgumentLength();

    /**
     * Retrieves the passed arguments as a single string.
     *
     * @return the argument line
     */
    String getArgumentLine();

    /**
     * Retrieves an argument from the specified index.
     *
     * @param index
     * @return
     */
    String getArgument(int index);

    /**
     * Retrieves the passed arguments.
     *
     * @return all passed arguments, split via whitespace
     */
    String[] getArguments();

    /**
     * Retrieves the command label used in association with this input.
     *
     * @return the command label
     */
    String getCommandLabel();

    /**
     * Retrieves the command line used to execute a specific {@link Command}.
     *
     * @return the passed command line
     */
    String getCommandLine();
}
