package io.github.disbatch.command.parameter.model;

import com.google.common.reflect.TypeToken;
import io.github.disbatch.command.CommandInput;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONAware;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Optional;

/**
 * @param <S> {@inheritDoc}
 * @param <V> {@inheritDoc}
 */
public final class JsonParameter<S extends CommandSender, V extends JSONAware> extends AbstractParameter<S, V> {
    private static final JSONParser PARSER = new JSONParser();

    private final Class<? super V> type;

    /**
     * @param jsonLabel
     */
    public JsonParameter(final @NotNull String jsonLabel) {
        super(jsonLabel);
        type = new TypeToken<V>(getClass()) {
        }.getRawType();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<V> parse(final CommandInput input, final S sender) {
        try {
            final Object result = PARSER.parse(input.getArgument(0));
            return type.isAssignableFrom(result.getClass()) ? Optional.of((V) result) : Optional.empty();
        } catch (final ParseException ignored) {
            return Optional.empty();
        }
    }
}
