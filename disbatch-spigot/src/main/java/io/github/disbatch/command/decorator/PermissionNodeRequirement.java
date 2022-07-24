package io.github.disbatch.command.decorator;

import com.google.common.base.Strings;
import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.builder.TabCompletions;
import io.github.disbatch.command.proxy.CommandProxy;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.StringJoiner;

/**
 * @param <T>
 */
public final class PermissionNodeRequirement<T extends CommandSender> extends CommandProxy<T> {
    private final String requiredPermissionNode;
    private final String noPermissionMessage;

    /**
     * @param innerCommand
     * @param requiredPermissionNode
     * @param noPermissionMessage
     */
    public PermissionNodeRequirement(final @NotNull Command<T> innerCommand, final @NotNull String requiredPermissionNode, final String noPermissionMessage) {
        super(innerCommand);
        this.requiredPermissionNode = requiredPermissionNode;
        this.noPermissionMessage = noPermissionMessage;
    }

    @Override
    public void execute(final T sender, final @NotNull CommandInput input) {
        if (sender.hasPermission(requiredPermissionNode))
            super.execute(sender, input);
        else if (!Strings.isNullOrEmpty(noPermissionMessage))
            sender.sendMessage(noPermissionMessage.replace("%permission", requiredPermissionNode));
    }

    @Override
    public List<String> tabComplete(final T sender, final @NotNull CommandInput input) {
        return sender.hasPermission(requiredPermissionNode)
                ? super.tabComplete(sender, input)
                : TabCompletions.emptyList();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("requiredPermissionNode='" + requiredPermissionNode + "'")
                .add("innerCommand=" + super.toString())
                .toString();
    }
}
