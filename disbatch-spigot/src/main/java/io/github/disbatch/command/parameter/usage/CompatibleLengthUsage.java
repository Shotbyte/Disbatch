package io.github.disbatch.command.parameter.usage;

/**
 *
 */
public interface CompatibleLengthUsage extends ParameterUsage {

    /**
     * @param fallbackUsage
     * @return
     */
    ParameterUsage orElse(ParameterUsage fallbackUsage);
}
