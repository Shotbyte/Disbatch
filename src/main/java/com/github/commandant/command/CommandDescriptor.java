package com.github.commandant.command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public final class CommandDescriptor {
    private final List<String> aliases = new LinkedList<>();
    private final String description;

    public CommandDescriptor(final String description, final String... aliases) {
        this.description = description;
        this.aliases.addAll(Arrays.asList(aliases));
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getDescription() {
        return description;
    }
}
