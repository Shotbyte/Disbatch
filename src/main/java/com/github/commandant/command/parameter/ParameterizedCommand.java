package com.github.commandant.command.parameter;

import com.github.commandant.command.Command;
import com.github.commandant.command.parameter.model.Parameter;
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

    protected ParameterizedCommand(final Parameter<? super K, V> parameter, final ParameterUsage usage) {
        this.parameter = parameter;
        this.usage = usage;
    }

    @Override
    public final void execute(final K sender, final String[] args) {
        final ParameterArgumentation argumentation = new ParameterArgumentation(args, parameter);

        if (args.length <= parameter.getSize() && parameter.canParse(argumentation))
            execute(sender, parameter.parse(argumentation, sender));
        else
            sender.sendMessage(usage.toString(parameter));
    }

    /**
     * @param sender
     * @param argument
     */
    protected abstract void execute(final K sender, final V argument);

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