package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.ParameterUsage;
import com.github.commandant.command.parameter.ParameterizedCommand;
import org.bukkit.command.CommandSender;

/**
 * Allows the creation or retrieval of any {@link Object} to be used in the execution phase of a
 * {@link ParameterizedCommand} without posing the burden of performing various checks to ensure that a specific set of
 * arguments is fit for parsing, which can also be dependent on the {@link CommandSender}.
 *
 * @param <K> any type extending {@link CommandSender} required to parse arguments
 * @param <V> the type of the resulting {@link Object} parsed from arguments
 * @see AbstractParameter
 * @see SenderIndependentParameter
 */
public interface Parameter<K extends CommandSender, V> {

    /**
     * Determines if the given {@link String} array can be parsed to an {@link Object} matching type parameter {@link V}.
     *
     * @param args the arguments passed from a {@link ParameterizedCommand}
     * @return if the passed arguments can be parsed
     */
    boolean canParse(String[] args);

    /**
     * Parses the given {@link String} array into an {@link Object} matching type parameter {@link V}.
     *
     * @param args   the arguments passed from a {@link ParameterizedCommand}
     * @param sender the {@link CommandSender} required to parse the arguments
     * @return the parsed result
     */
    V parse(String[] args, K sender);

    /**
     * @param commandLabel
     * @param usage
     * @return
     */
    String createUsageMessage(final String commandLabel, ParameterUsage usage);

    /**
     * Retrieves the minimum amount of arguments a {@link ParameterizedCommand} can pass.
     *
     * @return the minimum argument amount
     */
    int getMinimumUsage();

    /**
     * Retrieves the maximum amount of arguments a {@link ParameterizedCommand} can pass.
     *
     * @return the maximum argument amount
     */
    int getMaximumUsage();
}
