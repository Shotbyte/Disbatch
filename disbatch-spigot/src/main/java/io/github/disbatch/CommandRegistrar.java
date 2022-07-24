package io.github.disbatch;

import io.github.disbatch.command.CommandDescriptor;
import io.github.disbatch.command.proxy.TypedCommandProxy;

interface CommandRegistrar {
    void register(TypedCommandProxy typedCommand, CommandDescriptor descriptor);
}
