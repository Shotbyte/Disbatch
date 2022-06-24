package com.github.commandant.command.group;

import com.github.commandant.command.Command;
import com.github.commandant.command.proxy.TypedCommandProxy;

class GroupedCommand extends TypedCommandProxy {
    GroupedCommand(final Command<?> innerCommand) {
        super(innerCommand);
    }
}
