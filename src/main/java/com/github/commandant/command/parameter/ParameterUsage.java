package com.github.commandant.command.parameter;

/**
 * Encapsulates various {@link String} and {@code char} components to create a usage message for any
 * {@link ParameterizedCommand}.
 */
public class ParameterUsage {
    private final String baseMessage;
    private final char labelHead;
    private final char labelTail;

    /**
     * @param baseMessage the base message, optionally with the {@code %usage} placeholder (e.g. {@code "Usage: %usage"})
     *                    for including the command line usage, to use upon creation
     * @param labelHead   the {@code char} to be prepended to a usage label
     * @param labelTail   the {@code char} to be appended to a usage label
     */
    public ParameterUsage(final String baseMessage, final char labelHead, final char labelTail) {
        this.baseMessage = baseMessage;
        this.labelHead = labelHead;
        this.labelTail = labelTail;
    }

    public final String toMessage(final String commandLabel, final String... usageLabels) {
        final StringBuilder builder = new StringBuilder("/").append(commandLabel);

        for (final String label : usageLabels)
            builder.append(" ").append(labelHead).append(label).append(labelTail);

        return baseMessage.replace("%usage", builder);
    }
}
