package io.github.disbatch.command.parameter.builder;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;

/**
 * Serves the same purpose and functionality as {@link Parameter#parse(io.github.disbatch.command.CommandInput, CommandSender)}.
 */
@FunctionalInterface
public interface ParameterParser<K extends CommandSender, V> {

    /**
     * See {@link ParameterParser}.
     */
    V parse(CommandInput input, K sender);
}
