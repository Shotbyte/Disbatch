package io.github.disbatch;

import io.github.disbatch.command.parameter.ParameterizedCommand;
import io.github.disbatch.command.parameter.usage.ParameterUsage;
import io.github.disbatch.command.parameter.usage.ParameterUsages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;

public class EntityTypeCommandTest extends EasyMockSupport {
    private ParameterizedCommand<CommandSender, EntityType> caseInsensitiveCmd;
    private ParameterizedCommand<CommandSender, EntityType> caseSensitiveCmd;

    @Mock
    private CommandSender senderMock;

    @Before
    public void setup() {
        final ParameterUsage usage = ParameterUsages.withChevrons("%usage");
        caseInsensitiveCmd = new EntityTypeCaseInsensitiveCommand(usage);
        caseSensitiveCmd = new EntityTypeCaseSensitiveCommand(usage);
    }

    @Test
    public void testEntityTypeCommandExecution() {
        final String entityTypeName = EntityType.SHEEP.name();

        caseInsensitiveCmd.execute(senderMock, new LazyLoadingCommandInput(entityTypeName, "insensitive"));
        caseSensitiveCmd.execute(senderMock, new LazyLoadingCommandInput(entityTypeName, "sensitive"));
    }
}
