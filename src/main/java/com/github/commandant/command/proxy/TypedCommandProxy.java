package com.github.commandant.command.proxy;

import com.github.commandant.command.Command;
import com.google.common.base.Strings;
import org.bukkit.command.CommandSender;

import java.lang.reflect.ParameterizedType;

public class TypedCommandProxy extends CommandProxy<CommandSender> {
    private final Class<?> senderType;

    @SuppressWarnings("unchecked")
    public TypedCommandProxy(final Command<?> innerCommand) {
        super((Command<CommandSender>) innerCommand);
        senderType = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void execute(final CommandSender sender, final String commandLabel, final String[] args) {
        if (senderType.isAssignableFrom(sender.getClass()))
            innerCommand.execute(sender, commandLabel, args);
        else if (!Strings.isNullOrEmpty(getValidSenderMessage()))
            sender.sendMessage(getValidSenderMessage());
    }
}
