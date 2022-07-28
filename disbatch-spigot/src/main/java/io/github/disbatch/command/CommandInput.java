package io.github.disbatch.command;

/**
 *
 */
public interface CommandInput {

    /**
     * @return
     */
    int getArgumentLength();

    /**
     * @return
     */
    String getArgumentLine();

    /**
     * @param index
     * @return
     */
    String getArgument(int index);

    /**
     * @return
     */
    String[] getArguments();

    /**
     * @return
     */
    String getCommandLabel();

    /**
     * @return
     */
    String getCommandLine();
}
