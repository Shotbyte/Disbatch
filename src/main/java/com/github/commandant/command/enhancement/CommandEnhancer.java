package com.github.commandant.command.enhancement;

import com.github.commandant.command.Command;
import com.github.commandant.command.enhancement.model.CommandEnhancement;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public final class CommandEnhancer {
    private final List<CommandEnhancement> enhancements = new LinkedList<>();

    /**
     * @param enhancement
     * @return
     */
    public CommandEnhancer with(final CommandEnhancement enhancement) {
        enhancements.add(enhancement);
        return this;
    }

    /**
     * @param command
     * @return
     */
    public Command<?> enhance(final Command<?> command) {
        Command<?> result = command;

        for (final CommandEnhancement enhancement : enhancements)
            result = enhancement.applyTo(result);

        return result;
    }

    /**
     * @param command
     * @param enhancements
     * @return
     */
    public Command<?> enhance(final Command<?> command, final CommandEnhancement... enhancements) {
        Command<?> result = command;

        for (final CommandEnhancement enhancement : enhancements)
            result = enhancement.applyTo(result);

        return enhance(result);
    }
}
