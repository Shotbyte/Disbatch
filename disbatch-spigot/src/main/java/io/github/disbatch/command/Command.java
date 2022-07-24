package io.github.disbatch.command;

import io.github.disbatch.Disbatch;
import io.github.disbatch.command.builder.CommandBuilder;
import io.github.disbatch.command.builder.TabCompletions;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Represents an executable command within a Minecraft server running a Spigot implementation, which acts based on
 * various user inputs.
 *
 * @param <T> any type extending {@link CommandSender} that is allowed to execute the command
 * @apiNote Not to be confused with {@link org.bukkit.command.Command}.
 * @see Disbatch#register(Command, CommandDescriptor)
 * @see CommandBuilder
 */
public interface Command<T extends CommandSender> {

    /**
     * Executes the command.
     *
     * @param sender the source responsible for execution
     * @param input  all passed arguments, split via single whitespace
     */
    void execute(T sender, @NotNull CommandInput input);

    /**
     * Executed on tab completion, returning a list of options the {@link CommandSender} can tab through.
     *
     * @param sender the source responsible for initiating a tab completion
     * @param input  all passed arguments, split via whitespace
     * @return a list of tab completions for the specified arguments, which may be empty or immutable
     */
    default List<String> tabComplete(final T sender, final @NotNull CommandInput input) {
        return TabCompletions.emptyList();
    }
}
