package com.github.commandant;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @param <T>
 */
public class PermissionRequirement<T extends CommandSender> implements CommandModel<T> {
    private final CommandModel<T> innerCommand;
    private final String requiredPermission;
    private final String noPermissionMessage;

    public PermissionRequirement(final CommandModel<T> innerCommand, final String requiredPermission) {
        this(innerCommand, requiredPermission, null);
    }

    public PermissionRequirement(final CommandModel<T> innerCommand, final String requiredPermission, final String noPermissionMessage) {
        this.innerCommand = innerCommand;
        this.requiredPermission = requiredPermission;
        this.noPermissionMessage = noPermissionMessage;
    }

    @Override
    public final void execute(final T sender, final String[] args) {
        if (sender.hasPermission(requiredPermission))
            innerCommand.execute(sender, args);
        else if (noPermissionMessage != null && !noPermissionMessage.isEmpty())
            sender.sendMessage(noPermissionMessage.replace("%permission", requiredPermission));
    }

    @Override
    public final List<String> tabComplete(final T sender, final String[] args) {
        return innerCommand.tabComplete(sender, args);
    }

    @Override
    public final String getLabel() {
        return innerCommand.getLabel();
    }
}
