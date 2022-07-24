package io.github.disbatch.command;

/**
 *
 */
public interface CommandInput {

    /**
     * @return
     */
    static CommandInput empty() {
        return EmptyCommandInput.INSTANCE;
    }

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
    String[] getAllCommandLabels();

    /**
     * @return
     */
    String getRelativeCommandLabel();

    /**
     * @return
     */
    String getCommandLine();
}
