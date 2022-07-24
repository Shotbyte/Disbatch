package io.github.disbatch.command.parameter.builder;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;

import java.util.Collection;

/**
 * Serves the same purpose and functionality as {@link Parameter#getSuggestions(CommandSender, CommandInput)}.
 */
@FunctionalInterface
public interface Suggester<K> {

    /**
     * See {@link Suggester}.
     */
    Collection<String> getSuggestions(K sender, CommandInput input);
}
