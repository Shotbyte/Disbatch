package com.github.commandant;

import com.github.commandant.command.IdentifiableCommand;

class IdentifiableCommandAdapter extends CommandAdapter {
    IdentifiableCommandAdapter(final IdentifiableCommand<?> command) {
        super(command);
        setAliases(command.getAliases());
        setDescription(command.getDescription());
    }
}
