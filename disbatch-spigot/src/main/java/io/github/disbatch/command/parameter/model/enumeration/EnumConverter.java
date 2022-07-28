package io.github.disbatch.command.parameter.model.enumeration;

import org.jetbrains.annotations.Nullable;

/**
 * @param <E>
 */
@FunctionalInterface
public interface EnumConverter<E extends Enum<E>> {

    /**
     * @param nameArgument
     * @param directory
     * @return
     */
    @Nullable E convertWith(String nameArgument, EnumDirectory<E> directory);
}
