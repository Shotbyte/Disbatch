package com.github.commandant;

import com.github.commandant.command.Command;
import com.github.commandant.command.CommandDescriptor;

class IdentifiableCommandAdapter extends CommandAdapter {
    IdentifiableCommandAdapter(final Command<?> command, final CommandDescriptor descriptor) {
        super(command);
        setAliases(descriptor.getAliases());
        setDescription(descriptor.getDescription());
    }
}
