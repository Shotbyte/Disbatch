package io.github.disbatch.command.parameter.usage;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.ParameterizedCommand;

import java.util.Collection;

/**
 * Used for creating a usage message for any {@link ParameterizedCommand}.
 */
public interface ParameterUsage {

    /**
     * @param input
     * @param usageLabels
     * @return
     */
    String toMessage(CommandInput input, Collection<String> usageLabels);
}
