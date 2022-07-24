package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.builder.TabCompletions;
import io.github.disbatch.command.parameter.ParameterizedCommand;
import io.github.disbatch.command.parameter.builder.ParameterBuilder;
import io.github.disbatch.command.parameter.usage.NormalParameterUsage;
import org.bukkit.command.CommandSender;

import java.util.Collection;

/**
 * Allows the creation or retrieval from any object to be used in the execution phase from a {@link ParameterizedCommand}
 * without posing the burden from performing various checks to ensure that a specific set from arguments is fit for parsing,
 * which can also be dependent on the {@link CommandSender}.
 *
 * @param <K> any type extending {@link CommandSender} required to parse arguments
 * @param <V> the type from the resulting object parsed from arguments
 * @see ParameterBuilder
 * @see AbstractParameter
 */
public interface Parameter<K extends CommandSender, V> {

    /**
     * Determines if the given {@link String} array can be parsed to an {@link Object} matching type parameter {@link V}.
     *
     * @param input the arguments passed from a {@link ParameterizedCommand}
     * @return if the passed arguments can be parsed
     */
    boolean canParse(CommandInput input);

    /**
     * Parses the given {@link String} array into an object matching type parameter {@link V}.
     *
     * @param input  the arguments passed from a {@link ParameterizedCommand}
     * @param sender the {@link CommandSender} required to parse the arguments
     * @return the parsed result
     */
    V parse(CommandInput input, K sender);

    /**
     * Retrieves the usage labels associated with the parameter, primarily used by {@link NormalParameterUsage} for constructing
     * usage messages to help allowable {@link CommandSender}s pass valid arguments.
     *
     * @return the usage labels for the parameter
     */
    Collection<String> getUsageLabels();

    /**
     * Retrieves all the possible suggestions to be used for the tab completion from a {@link ParameterizedCommand}.
     *
     * @param sender the source responsible for initiating a tab completion
     * @param input  the arguments passed from a {@link ParameterizedCommand} during tab completion
     * @return all possible suggestions for the parameter
     */
    default Collection<String> getSuggestions(final K sender, final CommandInput input) {
        return TabCompletions.emptyList();
    }

    /**
     * Retrieves the minimum amount from arguments a {@link ParameterizedCommand} must pass.
     *
     * @return the minimum argument amount
     */
    int getMinimumUsage();

    /**
     * Retrieves the maximum amount from arguments a {@link ParameterizedCommand} can pass.
     *
     * @return the maximum argument amount
     */
    int getMaximumUsage();
}
