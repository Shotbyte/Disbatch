package com.github.commandant.command.parameter.model;

/**
 * Forms a {@link String} from all passed arguments, joined via single whitespace, within a nearly-infinite usage
 * span by default.
 */
public class StringParameter extends SenderIndependentParameter<String> {
    private final int usageSpan;

    public StringParameter(final String usageLabel) {
        this(usageLabel, Integer.MAX_VALUE);
    }

    public StringParameter(final String usageLabel, final int usageSpan) {
        super(usageLabel);
        this.usageSpan = usageSpan;
    }

    @Override
    public boolean canParse(final String[] args) {
        for (final String arg : args)
            if (arg.isEmpty()) return false;

        return true;
    }

    @Override
    protected String parse(final String[] args) {
        return String.join(" ", args);
    }

    @Override
    public int getUsageSpan() {
        return usageSpan;
    }
}
