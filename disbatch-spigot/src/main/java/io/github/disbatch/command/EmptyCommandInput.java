package io.github.disbatch.command;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.StringJoiner;

class EmptyCommandInput implements CommandInput {
    static final EmptyCommandInput INSTANCE = new EmptyCommandInput();

    private EmptyCommandInput() {
    }

    @Override
    public int getArgumentLength() {
        return 0;
    }

    @Override
    public String getArgumentLine() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getArgument(final int index) {
        return StringUtils.EMPTY;
    }

    @Override
    public String[] getArguments() {
        return ArrayUtils.EMPTY_STRING_ARRAY;
    }

    @Override
    public String[] getAllCommandLabels() {
        return ArrayUtils.EMPTY_STRING_ARRAY;
    }

    @Override
    public String getRelativeCommandLabel() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getCommandLine() {
        return StringUtils.EMPTY;
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
                .add("commandLine=''")
                .toString();
    }
}
