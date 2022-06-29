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
public final class PermissionNodeRequirement<T extends CommandSender> extends CommandProxy<T> {
    private final String requiredPermissionNode;
    private final String noPermissionMessage;

    public PermissionNodeRequirement(final Command<T> innerCommand, final String requiredPermissionNode, final String noPermissionMessage) {
        super(innerCommand);
        this.requiredPermissionNode = Objects.requireNonNull(requiredPermissionNode);
        this.noPermissionMessage = noPermissionMessage;
    }

    @Override
    public void execute(final T sender, final String commandLabel, final String[] args) {
        if (sender.hasPermission(requiredPermissionNode))
            super.execute(sender, commandLabel, args);
        else if (noPermissionMessage != null && !noPermissionMessage.isEmpty())
            sender.sendMessage(noPermissionMessage.replace("%permission", requiredPermissionNode));
    }

    @Override
    public List<String> tabComplete(final T sender, final String[] args) {
        return sender.hasPermission(requiredPermissionNode)
                ? super.tabComplete(sender, args)
                : Collections.emptyList();
    }
}
