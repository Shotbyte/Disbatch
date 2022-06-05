package com.github.commandant.command.enhancement;

import com.github.commandant.command.Command;
import com.github.commandant.command.enhancement.model.CommandEnhancement;

import java.util.LinkedList;
import java.util.List;

public final class CommandEnhancer {
    private final List<CommandEnhancement> enhancements = new LinkedList<>();

    public CommandEnhancer with(final CommandEnhancement enhancement) {
        enhancements.add(enhancement);
        return this;
    }

    public Command<?> enhance(final Command<?> command) {
        Command<?> result = command;

        for (final CommandEnhancement enhancement : enhancements)
            result = enhancement.applyTo(result);

        return result;
    }

    public Command<?> enhance(final Command<?> command, final CommandEnhancement... enhancements) {
        Command<?> result = command;

        for (final CommandEnhancement enhancement : enhancements)
            result = enhancement.applyTo(result);

        return enhance(result);
    }
}
