package com.github.commandant.command.parameter;

import com.github.commandant.command.Command;
import com.github.commandant.command.parameter.model.Parameter;
import com.google.common.base.Strings;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * @param <K>
 * @param <V>
 */
public abstract class ParameterizedCommand<K extends CommandSender, V> implements Command<K> {
    private final Parameter<? super K, V> parameter;
    private final ParameterUsage usage;
    private final String invalidArgumentMessage;

    protected ParameterizedCommand(final Parameter<? super K, V> parameter, final ParameterUsage usage) {
        this(parameter, usage, null);
    }

    protected ParameterizedCommand(final Parameter<? super K, V> parameter, final ParameterUsage usage, final String invalidArgumentMessage) {
        this.parameter = parameter;
        this.usage = usage;
        this.invalidArgumentMessage = invalidArgumentMessage;
    }

    @Override
    public final void execute(final K sender, final String aliasLabel, final String[] args) {
        if (args.length == parameter.getSize())
            sender.sendMessage(usage.toMessage(aliasLabel, parameter.getLabels()));
        else if (parameter.canParse(args))
            execute(sender, aliasLabel, parameter.parse(args, sender));
        else if (!Strings.isNullOrEmpty(invalidArgumentMessage))
            sender.sendMessage(usage.toMessage(aliasLabel, parameter.getLabels()));
        else
            sender.sendMessage(invalidArgumentMessage);
    }

    /**
     * @param sender
     * @param aliasLabel
     * @param argument
     */
    protected abstract void execute(final K sender, final String aliasLabel, final V argument);

    @Override
    public final List<String> tabComplete(final K sender, final String[] args) {
        return args.length <= parameter.getSize()
                ? tabComplete(sender, args[args.length - 1])
                : Collections.emptyList();
    }

    /**
     * @param sender
     * @param argument
     * @return
     */
    protected List<String> tabComplete(final K sender, final String argument) {
        return Collections.emptyList();
    }
}