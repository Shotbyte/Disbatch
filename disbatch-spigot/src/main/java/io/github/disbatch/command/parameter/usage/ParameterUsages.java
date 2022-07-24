package io.github.disbatch.command.parameter.usage;

/**
 *
 */
public final class ParameterUsages {
    private ParameterUsages() {
        throw new AssertionError();
    }

    /**
     * @param baseMessage
     * @return
     */
    public static ParameterUsage withChevrons(final String baseMessage) {
        return new NormalParameterUsage(baseMessage, '<', '>');
    }

    /**
     * @param baseMessage
     * @return
     */
    public static ParameterUsage withCurlyBraces(final String baseMessage) {
        return new NormalParameterUsage(baseMessage, '{', '}');
    }

    /**
     * @param baseMessage
     * @return
     */
    public static ParameterUsage withSquareBraces(final String baseMessage) {
        return new NormalParameterUsage(baseMessage, '[', ']');
    }
}
