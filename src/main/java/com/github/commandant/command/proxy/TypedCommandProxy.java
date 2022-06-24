package com.github.commandant.command.proxy;

import com.github.commandant.command.Command;
import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import org.bukkit.command.CommandSender;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class TypedCommandProxy extends CommandProxy<CommandSender> {
    private final Class<?> senderType;

    @SuppressWarnings("unchecked")
    public TypedCommandProxy(final Command<?> innerCommand) {
        super((Command<CommandSender>) innerCommand);
        senderType = extractSenderType(innerCommand);
    }

    private Class<?> extractSenderType(final Command<?> command) {
        for (final TypeToken<?> type : TypeToken.of(command.getClass()).getTypes()) {
            if (type.getRawType().equals(Command.class)) {
                final Type typeArgument = ((ParameterizedType) type.getType()).getActualTypeArguments()[0];

                if (typeArgument instanceof Class)
                    return (Class<?>) typeArgument;
            }
        }

        return CommandSender.class;
    }

    @Override
    public void execute(final CommandSender sender, final String commandLabel, final String[] args) {
        if (senderType.isAssignableFrom(sender.getClass()))
            super.execute(sender, commandLabel, args);
        else if (!Strings.isNullOrEmpty(getValidSenderMessage()))
            sender.sendMessage(getValidSenderMessage());
    }

    @Override
    public List<String> tabComplete(final CommandSender sender, final String[] args) {
        return senderType.isAssignableFrom(sender.getClass())
                ? super.tabComplete(sender, args)
                : Collections.emptyList();
    }
}
