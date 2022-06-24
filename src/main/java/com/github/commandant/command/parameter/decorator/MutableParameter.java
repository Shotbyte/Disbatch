package com.github.commandant.command.parameter.decorator;

import com.github.commandant.command.parameter.model.Parameter;
import lombok.experimental.Delegate;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * @param <K>
 * @param <V>
 */
public final class MutableParameter<K extends CommandSender, V> implements Parameter<K, V> {

    @Delegate
    private Parameter<K, V> underlyingParameter;

    /**
     * @param underlyingParameter
     */
    public void setUnderlyingParameter(final @NotNull Parameter<K, V> underlyingParameter) {
        this.underlyingParameter = underlyingParameter;
    }
}
