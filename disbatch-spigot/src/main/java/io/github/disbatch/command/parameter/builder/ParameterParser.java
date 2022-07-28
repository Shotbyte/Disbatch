package io.github.disbatch.command.parameter.builder;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;

import java.util.Optional;

/**
 * Serves the same purpose and functionality as {@link Parameter#parse(io.github.disbatch.command.CommandInput, CommandSender)}.
 */
@FunctionalInterface
public interface ParameterParser<S extends CommandSender, V> {

    /**
     * See {@link ParameterParser}.
     */
    Optional<V> parse(CommandInput input, S sender);
}
