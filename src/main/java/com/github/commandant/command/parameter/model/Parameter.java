package com.github.commandant.command.parameter.model;

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
     * Retrieves the labels that a {@link ParameterizedCommand} should use to display a usage message to any relative
     * {@link CommandSender} who passes flawed arguments.
     *
     * @return the correct usage labels
     * @apiNote The relative {@link ParameterizedCommand} will deem any argument input as invalid if the retrieved usage
     * labels' {@code length} is greater than the {@code length} of the input.
     */
    String[] getUsageLabels();

    /**
     * Retrieves the max extent of arguments a {@link ParameterizedCommand} can pass to the parameter, separated via
     * single whitespace.
     *
     * @return the argument usage span
     * @apiNote If the usage span is less than the {@code length} of {@link Parameter#getUsageLabels()}, the relative
     * {@link ParameterizedCommand} will immediately deem the argument input as invalid.
     */
    int getUsageSpan();
}
