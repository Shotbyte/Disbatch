package io.github.disbatch.command.parameter.model.enumeration;

import java.util.Locale;

/**
 *
 */
public final class EnumConverters {
    private static EnumConverter<?> LOWERCASE_INSENSITIVE;
    private static EnumConverter<?> UPPERCASE_INSENSITIVE;
    private static EnumConverter<?> CASE_SENSITIVE;

    private EnumConverters() {
        throw new AssertionError();
    }

    /**
     * @param <E>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> EnumConverter<E> lowerCaseInsensitive() {
        return LOWERCASE_INSENSITIVE == null
                ? (EnumConverter<E>) (LOWERCASE_INSENSITIVE = (EnumConverter<E>) (nameArgument, directory) -> directory.get(nameArgument.toLowerCase(Locale.ROOT)))
                : (EnumConverter<E>) LOWERCASE_INSENSITIVE;
    }

    /**
     * @param <E>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> EnumConverter<E> upperCaseInsensitive() {
        return UPPERCASE_INSENSITIVE == null
                ? (EnumConverter<E>) (UPPERCASE_INSENSITIVE = (EnumConverter<E>) (nameArgument, directory) -> directory.get(nameArgument.toUpperCase(Locale.ROOT)))
                : (EnumConverter<E>) UPPERCASE_INSENSITIVE;
    }

    /**
     * @param <E>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> EnumConverter<E> caseSensitive() {
        return CASE_SENSITIVE == null
                ? (EnumConverter<E>) (CASE_SENSITIVE = (EnumConverter<E>) (nameArgument, directory) -> directory.get(nameArgument))
                : (EnumConverter<E>) CASE_SENSITIVE;
    }
}
