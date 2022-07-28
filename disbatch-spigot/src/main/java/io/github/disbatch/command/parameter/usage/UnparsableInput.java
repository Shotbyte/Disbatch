package io.github.disbatch.command.parameter.usage;

import io.github.disbatch.command.CommandInput;

/**
 *
 */
public interface UnparsableInput extends CommandInput {

    /**
     * @return
     */
    boolean isCompatibleLength();
}
