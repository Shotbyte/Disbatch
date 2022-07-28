package io.github.disbatch.command.parameter.usage;

import java.util.Collection;
import java.util.Objects;

class CompatibleLengthProxy implements CompatibleLengthUsage {
    private final ParameterUsage meetsLengthUsage;
    private final ParameterUsage fallbackUsage;

    CompatibleLengthProxy(final ParameterUsage meetsLengthUsage, final ParameterUsage fallbackUsage) {
        this.meetsLengthUsage = Objects.requireNonNull(meetsLengthUsage);
        this.fallbackUsage = Objects.requireNonNull(fallbackUsage);
    }

    @Override
    public String toMessage(final UnparsableInput input, final Collection<String> usageLabels) {
        return input.isCompatibleLength()
                ? meetsLengthUsage.toMessage(input, usageLabels)
                : fallbackUsage.toMessage(input, usageLabels);
    }

    @Override
    public ParameterUsage orElse(final ParameterUsage fallbackUsage) {
        return meetsLengthUsage.equals(ParameterUsages.empty())
                ? new CompatibleLengthProxy(fallbackUsage, meetsLengthUsage)
                : new CompatibleLengthProxy(meetsLengthUsage, fallbackUsage);
    }
}
