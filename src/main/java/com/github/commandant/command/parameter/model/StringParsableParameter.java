package com.github.commandant.command.parameter.model;

import com.github.commandant.command.parameter.builder.Suggester;
import org.bukkit.command.CommandSender;

/**
 * @param <K>
 * @param <V>
 */
public abstract class StringParsableParameter<K extends CommandSender, V> extends AbstractParameter<K, V> {
    protected StringParsableParameter(final String... usageLabels) {
        super(usageLabels);
    }

    protected StringParsableParameter(final Suggester<K> suggester, final String... usageLabels) {
        super(suggester, usageLabels);
    }

    @Override
    public boolean canParse(final String[] args) {
        for (final String arg : args)
            if (arg.isEmpty()) return false;

        return true;
    }
}
