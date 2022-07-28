package io.github.disbatch;

import io.github.disbatch.command.CommandInput;

import java.util.Objects;
import java.util.StringJoiner;

class LazyLoadingCommandInput implements CommandInput {
    private final String[] arguments;
    private final String cmdLabel;
    private String argumentLine;
    private String commandLine;

    LazyLoadingCommandInput(final String argument, final String cmdLabel) {
        this(new String[]{argument}, cmdLabel);
    }

    LazyLoadingCommandInput(final String[] arguments, final String cmdLabel) {
        this.arguments = arguments;
        this.cmdLabel = cmdLabel;
    }

    @Override
    public String getArgument(final int index) {
        return arguments[index];
    }

    @Override
    public String[] getArguments() {
        return arguments;
    }

    @Override
    public String getCommandLabel() {
        return cmdLabel;
    }

    @Override
    public int getArgumentLength() {
        return arguments.length;
    }

    @Override
    public String getArgumentLine() {
        if (argumentLine == null) {
            if (arguments.length == 1) return (argumentLine = arguments[0]);
            return (argumentLine = String.join(" ", arguments));
        }

        return argumentLine;
    }

    @Override
    public String getCommandLine() {
        return commandLine == null
                ? (commandLine = String.join(" ", cmdLabel, getArgumentLine()))
                : commandLine;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandInput)) return false;
        final CommandInput that = (CommandInput) o;
        return getCommandLine().equals(that.getCommandLine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommandLine());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("commandLine=" + getCommandLine())
                .toString();
    }
}
