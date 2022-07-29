package io.github.disbatch.mock;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.ParameterizedCommand;
import io.github.disbatch.command.parameter.builder.Suggesters;
import io.github.disbatch.command.parameter.model.enumeration.EnumParameter;
import io.github.disbatch.command.parameter.usage.ParameterUsage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

public class EntityTypeCaseSensitiveCommand extends ParameterizedCommand<CommandSender, EntityType> {
    public EntityTypeCaseSensitiveCommand(final ParameterUsage usage) {
        super(new EnumParameter<>(EntityType.class, "type")
                .withSuggester(Suggesters.of(EntityType.values())), usage);
    }

    @Override
    protected void execute(final CommandSender sender, final CommandInput input, final EntityType type) {
        System.out.println("EntityType from case sensitive: " + type.name());
    }
}
