package com.github.commandant.command.enhance;

import com.github.commandant.command.Command;
import com.github.commandant.command.CommandProxy;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class PermissionRequirement implements CommandEnhancement {
    private final String requiredPermission;
    private final String noPermissionMessage;

    public PermissionRequirement(final String requiredPermission) {
        this(requiredPermission, null);
    }

    public PermissionRequirement(final String requiredPermission, final String noPermissionMessage) {
        this.requiredPermission = requiredPermission;
        this.noPermissionMessage = noPermissionMessage;
    }

    @Override
    public <T extends CommandSender> Command<T> applyTo(final Command<T> command) {
        return new PermissionRequiredCommand<>(command);
    }

    private class PermissionRequiredCommand<T extends CommandSender> extends CommandProxy<T> {
        private PermissionRequiredCommand(final Command<T> innerCommand) {
            super(innerCommand);
        }

        @Override
        public final void execute(final T sender, final String aliasLabel, final String[] args) {
            if (sender.hasPermission(requiredPermission))
                innerCommand.execute(sender, aliasLabel, args);
            else if (noPermissionMessage != null && !noPermissionMessage.isEmpty())
                sender.sendMessage(noPermissionMessage.replace("%permission", requiredPermission));
        }

        @Override
        public final List<String> tabComplete(final T sender, final String[] args) {
            return sender.hasPermission(requiredPermission)
                    ? innerCommand.tabComplete(sender, args)
                    : Collections.emptyList();
        }
    }
}
