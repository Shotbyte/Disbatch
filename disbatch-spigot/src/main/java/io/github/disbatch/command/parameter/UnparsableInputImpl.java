package io.github.disbatch.command.parameter;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.usage.UnparsableInput;

class UnparsableInputImpl implements UnparsableInput {
    private final CommandInput cmdInput;
    private final boolean isCompatibleLength;

    UnparsableInputImpl(final CommandInput cmdInput, final boolean isCompatibleLength) {
        this.cmdInput = cmdInput;
        this.isCompatibleLength = isCompatibleLength;
    }

    @Override
    public int getArgumentLength() {
        return cmdInput.getArgumentLength();
    }

    @Override
    public String getArgumentLine() {
        return cmdInput.getArgumentLine();
    }

    @Override
    public String getArgument(final int index) {
        return cmdInput.getArgument(index);
    }

    @Override
    public String[] getArguments() {
        return cmdInput.getArguments();
    }

    @Override
    public String getCommandLabel() {
        return cmdInput.getCommandLabel();
    }

    @Override
    public String getCommandLine() {
        return cmdInput.getCommandLine();
    }

    @Override
    public boolean isCompatibleLength() {
        return isCompatibleLength;
    }
}
