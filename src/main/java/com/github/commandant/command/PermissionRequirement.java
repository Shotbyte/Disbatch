package com.github.commandant.command;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @param <T>
 */
public class PermissionRequirement<T extends CommandSender> implements IdentifiableCommand<T> {
    private static final Supplier<String> EMPTY_DESCRIPTION = () -> "";

    private final Command<T> innerCommand;
    private final String requiredPermission;
    private final String noPermissionMessage;
    private final Supplier<String> description;
    private final Supplier<List<String>> aliases;

    public PermissionRequirement(final Command<T> innerCommand, final String requiredPermission, final String noPermissionMessage) {
        this(innerCommand, requiredPermission, noPermissionMessage, EMPTY_DESCRIPTION, Collections::emptyList);
    }

    public PermissionRequirement(final IdentifiableCommand<T> innerCommand, final String requiredPermission, final String noPermissionMessage) {
        this(innerCommand, requiredPermission, noPermissionMessage, innerCommand::getDescription, innerCommand::getAliases);
    }

    private PermissionRequirement(final Command<T> innerCommand, final String requiredPermission, final String noPermissionMessage, final Supplier<String> description, final Supplier<List<String>> aliases) {
        this.innerCommand = innerCommand;
        this.requiredPermission = requiredPermission;
        this.noPermissionMessage = noPermissionMessage;
        this.description = description;
        this.aliases = aliases;
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

    @Override
    public final String getLabel() {
        return innerCommand.getLabel();
    }

    @Override
    public final String getDescription() {
        return description.get();
    }

    @Override
    public final List<String> getAliases() {
        return aliases.get();
    }
}
