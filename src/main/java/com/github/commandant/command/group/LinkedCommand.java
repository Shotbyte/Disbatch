package com.github.commandant.command.group;

import com.github.commandant.command.Command;
import com.github.commandant.command.proxy.TypedCommandProxy;

class LinkedCommand extends TypedCommandProxy {
    LinkedCommand(final Command<?> innerCommand) {
        super(innerCommand);
    }
}
