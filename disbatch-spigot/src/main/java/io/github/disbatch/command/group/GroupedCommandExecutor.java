package io.github.disbatch.command.group;

import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;

import java.util.Objects;
import java.util.StringJoiner;

class GroupedCommandExecutor<T extends CommandSender> {
    private final Command<T> command;
    private final CommandInput previous;

    GroupedCommandExecutor(final Command<T> command, final CommandInput previous) {
        this.command = command;
        this.previous = previous;
    }

    void execute(final T sender) {
        command.execute(sender, new LazyLoadingGroupedCommandInput(previous));
    }

    private static class LazyLoadingGroupedCommandInput implements CommandInput {
        private final CommandInput previous;
        private final String previousCmdLabel;
        private String[] arguments;
        private String[] allCmdLabels;

        private LazyLoadingGroupedCommandInput(final CommandInput previous) {
            this.previous = previous;
            previousCmdLabel = previous.getRelativeCommandLabel();
        }

        @Override
        public int getArgumentLength() {
            return previous.getArgumentLength() - 1;
        }

        @Override
        public String getArgumentLine() {
            return previous.getArgumentLine();
        }

        @Override
        public String getArgument(final int index) {
            return getArguments()[index];
        }

        @Override
        public String[] getArguments() {
            if (arguments == null) {
                final String[] previousArguments = previous.getArguments();
                final String[] arguments = (this.arguments = new String[previousArguments.length - 1]);
                System.arraycopy(previousArguments, 1, arguments, 0, arguments.length);

                return arguments;
            }

            return arguments;
        }

        @Override
        public String[] getAllCommandLabels() {
            if (allCmdLabels == null) {
                final String[] allPreviousCmdLabels = previous.getAllCommandLabels();
                final int length = allPreviousCmdLabels.length;
                final String[] allCmdLabels = (arguments = new String[length + 1]);
                System.arraycopy(allPreviousCmdLabels, 1, allCmdLabels, 0, arguments.length);
                allCmdLabels[length - 1] = previousCmdLabel;

                return allCmdLabels;
            }

            return allCmdLabels;
        }

        @Override
        public String getRelativeCommandLabel() {
            return previousCmdLabel;
        }

        @Override
        public String getCommandLine() {
            return previous.getCommandLine();
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
}
