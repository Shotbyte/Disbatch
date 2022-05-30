package com.github.commandant;

import com.github.commandant.command.Command;
import com.google.common.base.Strings;
import net.jodah.typetools.TypeResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.List;

class CommandAdapter extends BukkitCommand {
    private final Command<CommandSender> command;
    private final Class<?> senderType;

    @SuppressWarnings("unchecked")
    CommandAdapter(final Command<?> command) {
        super(command.getLabel());
        this.command = (Command<CommandSender>) command;

        senderType = TypeResolver.resolveRawArgument(Command.class, command.getClass());
    }

    @Override
    public boolean execute(final CommandSender sender, final String aliasLabel, final String[] args) {
        if (senderType.isInstance(sender))
            command.execute(sender, aliasLabel, args);
        else if (!Strings.isNullOrEmpty(command.getValidSenderMessage()))
            sender.sendMessage(command.getValidSenderMessage());

        return true;
    }

    @Override
    public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) throws IllegalArgumentException {
        return command.tabComplete(sender, args);
    }
}
