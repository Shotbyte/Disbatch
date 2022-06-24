package com.github.commandant.command.parameter;

import lombok.experimental.UtilityClass;

/**
 *
 */
@UtilityClass
public class ParameterUsages {

    /**
     * @param baseMessage
     * @return
     */
    public ParameterUsage withChevrons(final String baseMessage) {
        return new ParameterUsage(baseMessage, '<', '>');
    }

    /**
     * @param baseMessage
     * @return
     */
    public ParameterUsage withCurlyBraces(final String baseMessage) {
        return new ParameterUsage(baseMessage, '{', '}');
    }

    /**
     * @param baseMessage
     * @return
     */
    public ParameterUsage withSquareBraces(final String baseMessage) {
        return new ParameterUsage(baseMessage, '[', ']');
    }
}
