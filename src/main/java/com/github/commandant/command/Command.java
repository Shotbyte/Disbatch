package com.github.commandant.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * Represents an executable command within a Minecraft server, which acts based on various user inputs.
 *
 * @param <T> any type extending {@link CommandSender} that is allowed to execute the command.
 * @apiNote Not to be confused with {@link org.bukkit.command.Command}.
 */
public interface Command<T extends CommandSender> {

    /**
     * Executes the command.
     *
     * @param sender       the source responsible for execution
     * @param commandLabel the alias of the command used
     * @param args         all passed arguments, split via single whitespace
     */
    void execute(T sender, String commandLabel, String[] args);

    /**
     * Executed on tab completion, returning a list of options the {@link CommandSender} can tab through.
     *
     * @param sender the source responsible for initiating a tab completion
     * @param args   all passed arguments, split via whitespace
     * @return a list of tab-completions for the specified arguments, which may be empty or immutable
     */
    default List<String> tabComplete(final T sender, final String[] args) {
        return Collections.emptyList();
    }

    /**
     * Retrieves the message to be sent to any {@link CommandSender} that is not allowed to execute the command.
     *
     * @return the valid sender message
     */
    default String getValidSenderMessage() {
        return StringUtils.EMPTY;
    }

    /**
     * Retrieves the label to be associated with the command upon registration.
     *
     * @return the command's label
     */
    String getLabel();
}
