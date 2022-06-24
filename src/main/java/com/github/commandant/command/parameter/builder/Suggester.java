package com.github.commandant.command.parameter.builder;

import com.github.commandant.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;

import java.util.Collection;

/**
 * Serves the same purpose and functionality as {@link Parameter#getSuggestions(CommandSender, String[])}.
 */
public interface Suggester<K> {

    /**
     * See {@link Suggester}
     */
    Collection<String> getSuggestions(K sender, String[] boundedArgs);
}
