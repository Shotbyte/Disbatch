package io.github.disbatch.command.parameter.model;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.builder.Suggester;
import org.bukkit.command.CommandSender;

/**
 * @param <K> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public abstract class StringParsableParameter<K extends CommandSender, V> extends AbstractParameter<K, V> {
    protected StringParsableParameter(final String... usageLabels) {
        super(usageLabels);
    }

    protected StringParsableParameter(final Suggester<K> suggester, final String... usageLabels) {
        super(suggester, usageLabels);
    }

    @Override
    public boolean canParse(final CommandInput input) {
        for (final String arg : input.getArguments())
            if (arg.isEmpty()) return false;

        return true;
    }
}
