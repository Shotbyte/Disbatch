package com.github.commandant.command.parameter;

import com.github.commandant.command.Command;
import com.google.common.base.Strings;
import net.jodah.typetools.TypeResolver;
import org.bukkit.command.CommandSender;

import java.util.List;

class SubcommandContainer implements Command<CommandSender> {
    private final Command<CommandSender> innerCommand;
    private final Class<?> senderType;

    @SuppressWarnings("unchecked")
    SubcommandContainer(final Command<?> innerCommand) {
        this.innerCommand = (Command<CommandSender>) innerCommand;
        senderType = TypeResolver.resolveRawArgument(Command.class, innerCommand.getClass());
    }

    @Override
    public void execute(final CommandSender sender, final String aliasLabel, final String[] args) {
        if (senderType.isInstance(sender))
            innerCommand.execute(sender, aliasLabel, args);
        else if (Strings.isNullOrEmpty(getValidSenderMessage()))
            sender.sendMessage(getValidSenderMessage());
    }

    @Override
    public List<String> tabComplete(final CommandSender sender, final String[] args) {
        return innerCommand.tabComplete(sender, args);
    }

    @Override
    public String getLabel() {
        return innerCommand.getLabel();
    }

    @Override
    public String getValidSenderMessage() {
        return innerCommand.getValidSenderMessage();
    }
}
