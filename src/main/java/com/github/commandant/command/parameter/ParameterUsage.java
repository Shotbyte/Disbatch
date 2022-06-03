package com.github.commandant.command.parameter;

/**
 *
 */
public class ParameterUsage {
    private final String usageMessage;
    private final char labelHead;
    private final char labelTail;

    public ParameterUsage(final String usageMessage, final char labelHead, final char labelTail) {
        this.usageMessage = usageMessage;
        this.labelHead = labelHead;
        this.labelTail = labelTail;
    }

    public final String toMessage(final String commandLabel, final String... labels) {
        final StringBuilder builder = new StringBuilder("/").append(commandLabel);

        for (final String label : labels)
            builder.append(" ").append(labelHead).append(label).append(labelTail);

        return usageMessage.replace("%usage", builder);
    }
}
