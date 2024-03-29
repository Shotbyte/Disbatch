package io.github.disbatch.command.parameter.model;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @param <S> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public final class GsonParameter<S extends CommandSender, V extends JsonElement> extends AbstractParameter<S, V> {
    private static final JsonParser PARSER = new JsonParser();

    private final Class<? super V> type;

    /**
     * @param jsonLabel
     */
    public GsonParameter(final @NotNull String jsonLabel) {
        super(jsonLabel);
        type = new TypeToken<V>(getClass()) {
        }.getRawType();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<V> parse(final CommandInput input, final S sender) {
        try {
            final JsonElement result = PARSER.parse(input.getArgumentLine());
            return type.isAssignableFrom(result.getClass()) ? Optional.of((V) result) : Optional.empty();
        } catch (final JsonSyntaxException ignored) {
            return Optional.empty();
        }
    }

    @Override
    public int getMaximumUsage() {
        return Integer.MAX_VALUE;
    }
}
