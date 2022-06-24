package com.github.commandant.command.group;

import org.bukkit.command.CommandSender;

class GroupedCommandExecutor<T extends CommandSender> {
    private final GroupedCommand command;
    private final String[] args;

    GroupedCommandExecutor(final GroupedCommand command, final String[] args) {
        this.command = command;
        this.args = args;
    }

    void execute(final T sender, final String aliasLabel) {
        command.execute(sender, aliasLabel + " " + command.getLabel(), args);
    }
}
