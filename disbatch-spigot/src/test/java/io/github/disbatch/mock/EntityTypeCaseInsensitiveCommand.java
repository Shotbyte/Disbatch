package io.github.disbatch.mock;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.ParameterizedCommand;
import io.github.disbatch.command.parameter.builder.Suggesters;
import io.github.disbatch.command.parameter.model.enumeration.EnumConverters;
import io.github.disbatch.command.parameter.model.enumeration.EnumParameter;
import io.github.disbatch.command.parameter.usage.ParameterUsage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class EntityTypeCaseInsensitiveCommand extends ParameterizedCommand<CommandSender, EntityType> {
    public EntityTypeCaseInsensitiveCommand(final @NotNull ParameterUsage usage) {
        super(new EnumParameter<>(EntityType.class, "type", EnumConverters.upperCaseInsensitive())
                .withSuggester(Suggesters.ofLowerCase(EntityType.values())), usage);
    }

    @Override
    protected void execute(final CommandSender sender, final CommandInput input, final EntityType type) {
        System.out.println("EntityType from case insensitive: " + type.name());
    }
}
