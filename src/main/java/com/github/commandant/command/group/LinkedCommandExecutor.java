package com.github.commandant.command.group;

import org.bukkit.command.CommandSender;

class LinkedCommandExecutor<T extends CommandSender> {
    private final LinkedCommand<T> command;
    private final String[] args;

    LinkedCommandExecutor(final LinkedCommand<T> command, final String[] args) {
        this.command = command;
        this.args = args;
    }

    void execute(final T sender, final String aliasLabel) {
        command.execute(sender, aliasLabel + " " + command.getLabel(), args);
    }
}
