package com.github.commandant.command.group;

import com.github.commandant.command.Command;
import com.github.commandant.command.CommandProxy;
import com.google.common.base.Strings;
import net.jodah.typetools.TypeResolver;
import org.bukkit.command.CommandSender;

class GroupedCommand<T extends CommandSender> extends CommandProxy<T> {
    private final Class<?> senderType;

    GroupedCommand(final Command<T> innerCommand) {
        super(innerCommand);
        senderType = TypeResolver.resolveRawArgument(Command.class, innerCommand.getClass());
    }

    @Override
    public void execute(final T sender, final String aliasLabel, final String[] args) {
        if (senderType.isInstance(sender))
            super.execute(sender, aliasLabel, args);
        else if (!Strings.isNullOrEmpty(getValidSenderMessage()))
            sender.sendMessage(getValidSenderMessage());
    }
}
