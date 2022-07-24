package io.github.disbatch.command.parameter.builder;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.model.Parameter;

/**
 * Serves the same purpose and functionality as {@link Parameter#canParse(io.github.disbatch.command.CommandInput)}.
 */
@FunctionalInterface
public interface ParameterPredicate {

    /**
     * See {@link ParameterPredicate}.
     */
    boolean canParse(CommandInput input);
}
