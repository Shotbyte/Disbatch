package com.github.commandant;

import java.util.Arrays;
import java.util.List;

public final class CommandDescriptor {
    private final String description;
    private final List<String> aliases;

    public CommandDescriptor(final String description, final String... aliases) {
        this.description = description;
        this.aliases = Arrays.asList(aliases);
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAliases() {
        return aliases;
    }
}
