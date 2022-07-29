package io.github.disbatch;

import io.github.disbatch.command.Command;
import io.github.disbatch.command.CommandDescriptor;
import io.github.disbatch.command.CommandInputs;
import io.github.disbatch.command.group.CommandGroup;
import io.github.disbatch.command.parameter.usage.ParameterUsages;
import io.github.disbatch.mock.DummyCommandLine;
import org.bukkit.command.CommandSender;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CommandGroupTest extends EasyMockSupport {
    private CommandGroup<CommandSender> cmdGroup;
    private CommandSender senderMock;

    @Before
    public void setup() {
        senderMock = createMock(CommandSender.class);

        cmdGroup = new CommandGroup<>("cmd", ParameterUsages.withChevrons("%usage"));

        senderMock.sendMessage("");
        EasyMock.expectLastCall().andAnswer(() -> {
            System.out.println("Sent to CommandSender: " + EasyMock.getCurrentArguments()[0]);
            return null;
        });

        final Command<CommandSender> cmd =
                (sender, input) -> System.out.printf("Arguments for %s: %s%n", input.getCommandLabel(), input.getArgumentLine());

        cmdGroup.addCommand(cmd, CommandDescriptor.label("cmd-1"));
        cmdGroup.addCommand(cmd, CommandDescriptor.label("cmd-2"));
    }

    @Test
    public void testCommandGroupTabCompletion() {
        final List<String> tabCompletion = cmdGroup.tabComplete(senderMock, new DummyCommandLine("cmd-3"));
        final List<String> emptyCompletion = cmdGroup.tabComplete(senderMock, CommandInputs.empty());

        System.out.println("Tab completion: " + tabCompletion);
        System.out.println("Empty tab completion: " + emptyCompletion);
    }

    @Test
    public void testGroupedCommandInput() {
        cmdGroup.execute(senderMock, new SingleLabelCommandInput("cmd"));
    }
}
