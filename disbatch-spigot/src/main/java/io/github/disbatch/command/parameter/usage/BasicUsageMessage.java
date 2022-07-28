package io.github.disbatch.command.parameter.usage;

import java.util.Collection;

/**
 *
 */
public final class BasicUsageMessage implements ParameterUsage {
    private final String message;

    /**
     * @param message
     */
    public BasicUsageMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toMessage(final UnparsableInput input, final Collection<String> usageLabels) {
        return message;
    }
}
