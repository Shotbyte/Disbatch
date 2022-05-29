package com.github.commandant.command.parameter;

import com.github.commandant.command.parameter.model.Parameter;

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

    public final String toString(final String commandLabel, final Parameter<?, ?> parameter) {
        final StringBuilder builder = new StringBuilder("/")
                .append(commandLabel)
                .append(" ")
                .append(parameterHead)
                .append(parameter.getLabel())
                .append(parameterTail)
                .append(" ");

        return usageMessage.replace("%usage", builder.substring(0, builder.length() - 1));
    }
}
