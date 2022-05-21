package com.github.commandant;

import net.jodah.typetools.TypeResolver;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

class CommandModelAdapter extends Command {
    private final CommandModel<CommandSender> command;
    private final Class<?> senderType;

    @SuppressWarnings("unchecked")
    CommandModelAdapter(final CommandModel<?> command) {
        super("");
        this.command = (CommandModel<CommandSender>) command;

        senderType = TypeResolver.resolveRawArgument(CommandModel.class, command.getClass());
    }

    @Override
    public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
        if (senderType.isInstance(sender.getClass()))
            command.execute(sender, args);

        return true;
    }

    @Override
    public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) throws IllegalArgumentException {
        return command.tabComplete(sender, args);
    }
}
