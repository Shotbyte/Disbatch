package io.github.disbatch.command.parameter.usage;

import org.apache.commons.lang.StringUtils;

/**
 *
 */
public final class ParameterUsages {
    private static final ParameterUsage EMPTY = (input, usageLabels) -> StringUtils.EMPTY;

    private ParameterUsages() {
        throw new AssertionError();
    }

    /**
     * @param usage
     * @return
     */
    public static CompatibleLengthUsage ifCompatibleLength(final ParameterUsage usage) {
        return new CompatibleLengthProxy(usage, empty());
    }

    /**
     * @return
     */
    public static ParameterUsage empty() {
        return EMPTY;
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
