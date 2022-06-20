package com.github.commandant.command.decorator;

import com.github.commandant.command.Command;
import com.github.commandant.command.proxy.CommandProxy;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @param <T>
 */
public final class PermissionRequirement<T extends CommandSender> extends CommandProxy<T> {
    private final String requiredPermission;
    private final String noPermissionMessage;

    public PermissionRequirement(final Command<T> innerCommand, final String requiredPermission, final String noPermissionMessage) {
        super(innerCommand);
        this.requiredPermission = Objects.requireNonNull(requiredPermission);
        this.noPermissionMessage = noPermissionMessage;
    }

    @Override
    public void execute(final T sender, final String commandLabel, final String[] args) {
        if (sender.hasPermission(requiredPermission))
            innerCommand.execute(sender, commandLabel, args);
        else if (noPermissionMessage != null && !noPermissionMessage.isEmpty())
            sender.sendMessage(noPermissionMessage.replace("%permission", requiredPermission));
    }

    @Override
    public List<String> tabComplete(final T sender, final String[] args) {
        return sender.hasPermission(requiredPermission)
                ? innerCommand.tabComplete(sender, args)
                : Collections.emptyList();
    }
}
