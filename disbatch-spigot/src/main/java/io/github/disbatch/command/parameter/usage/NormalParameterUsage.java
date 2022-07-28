package io.github.disbatch.command.parameter.usage;

import io.github.disbatch.command.parameter.ParameterizedCommand;

import java.util.Collection;

/**
 * Encapsulates various {@link String} and {@code char} components to create a usage message for any
 * {@link ParameterizedCommand}.
 */
public final class NormalParameterUsage implements ParameterUsage {
    private final String baseMessage;
    private final char labelHead;
    private final char labelTail;

    /**
     * @param baseMessage the base message, optionally with the {@code %usage} placeholder (e.g. {@code "Usage: %usage"})
     *                    for including the command line usage, to use upon creation
     * @param labelHead   the {@code char} to be prepended to a usage label
     * @param labelTail   the {@code char} to be appended to a usage label
     */
    public NormalParameterUsage(final String baseMessage, final char labelHead, final char labelTail) {
        this.baseMessage = baseMessage;
        this.labelHead = labelHead;
        this.labelTail = labelTail;
    }

    @Override
    public String toMessage(final UnparsableInput input, final Collection<String> usageLabels) {
        final StringBuilder builder = new StringBuilder("/").append(input.getCommandLabel());

        for (final String label : usageLabels)
            builder.append(" ").append(labelHead).append(label).append(labelTail);

        return baseMessage.replace("%usage", builder);
    }
}
