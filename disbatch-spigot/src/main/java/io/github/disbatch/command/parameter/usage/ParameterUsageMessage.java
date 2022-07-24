package io.github.disbatch.command.parameter.usage;

import io.github.disbatch.command.CommandInput;

import java.util.Collection;

public class ParameterUsageMessage implements ParameterUsage {
    private final String baseMessage;

    public ParameterUsageMessage(final String baseMessage) {
        this.baseMessage = baseMessage;
    }

    @Override
    public String toMessage(final CommandInput input, final Collection<String> usageLabels) {
        return baseMessage.replace("%commandLabels", String.join(" ", input.getAllCommandLabels()));
    }
}
