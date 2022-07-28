package io.github.disbatch.command.parameter.model.enumeration;

import org.jetbrains.annotations.Nullable;

/**
 * @param <E>
 */
public interface EnumDirectory<E extends Enum<E>> {

    /**
     * @param nameArgument
     * @return
     */
    @Nullable E get(String nameArgument);
}
