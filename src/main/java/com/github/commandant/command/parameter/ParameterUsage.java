package com.github.commandant.command.parameter;

/**
 *
 */
public class ParameterUsage {
    private final String usageMessage;
    private final char parameterHead;
    private final char parameterTail;

    public ParameterUsage(final String usageMessage, final char parameterHead, final char parameterTail) {
        this.usageMessage = usageMessage;
        this.parameterHead = parameterHead;
        this.parameterTail = parameterTail;
    }

    public final String toMessage(final String aliasLabel, final String[] labels) {
        final StringBuilder builder = new StringBuilder("/").append(aliasLabel);

        for (final String label : labels)
            builder.append(" ").append(parameterHead).append(label).append(parameterTail);

        return usageMessage.replace("%usage", builder);
    }
}
