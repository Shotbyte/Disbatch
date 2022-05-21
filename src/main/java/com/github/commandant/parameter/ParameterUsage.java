package com.github.commandant.parameter;

import com.github.commandant.parameter.model.Parameter;

/**
 *
 */
public final class ParameterUsage {
    private final String usageMessage;
    private final char parameterHead;
    private final char parameterTail;

    public ParameterUsage(final String usageMessage, final char parameterHead, final char parameterTail) {
        this.usageMessage = usageMessage;
        this.parameterHead = parameterHead;
        this.parameterTail = parameterTail;
    }

    public String toString(final Parameter<?, ?> parameter) {
        final StringBuilder builder = new StringBuilder()
                .append(parameterHead)
                .append(parameter.getLabel())
                .append(parameterTail)
                .append(" ");

        return usageMessage.replace("%usage", builder.substring(0, builder.length() - 1));
    }
}
