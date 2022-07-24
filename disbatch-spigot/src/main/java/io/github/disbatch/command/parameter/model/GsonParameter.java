package io.github.disbatch.command.parameter.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;

/**
 * @param <K>
 */
public final class GsonParameter<K extends CommandSender> extends StringParsableParameter<K, JsonElement> {
    private static final JsonParser PARSER = new JsonParser();

    @Override
    public boolean canParse(final CommandInput input) {
        try {
            PARSER.parse(input.getArgumentLine());
        } catch (final JsonSyntaxException ignored) {
            return false;
        }

        return true;
    }

    @Override
    public JsonElement parse(final CommandInput input, final K sender) {
        return PARSER.parse(input.getArgumentLine());
    }

    @Override
    public int getMaximumUsage() {
        return Integer.MAX_VALUE;
    }
}
