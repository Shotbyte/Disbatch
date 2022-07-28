package io.github.disbatch.command.parameter.model.enumeration;

import io.github.disbatch.command.CommandInput;
import io.github.disbatch.command.parameter.model.AbstractParameter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @param <S> {@inheritDoc}
 * @param <E> {@inheritDoc}
 */
public final class EnumParameter<S extends CommandSender, E extends Enum<E>> extends AbstractParameter<S, E> {
    private static final Map<Class<Enum<?>>, EnumDirectory<?>> ENUM_DIRECTORY_CACHE = new HashMap<>();

    private final EnumDirectory<E> directory;
    private final EnumConverter<E> converter;

    /**
     * @param type
     * @param enumLabel
     */
    public EnumParameter(final @NotNull Class<E> type, final @NotNull String enumLabel) {
        this(type, enumLabel, EnumConverters.caseSensitive());
    }

    /**
     * @param type
     * @param enumLabel
     * @param converter
     */
    @SuppressWarnings("unchecked")
    public EnumParameter(final @NotNull Class<E> type, final @NotNull String enumLabel, final @NotNull EnumConverter<E> converter) {
        super(enumLabel);
        this.converter = converter;

        directory = (EnumDirectory<E>) ENUM_DIRECTORY_CACHE.computeIfAbsent((Class<Enum<?>>) type,
                enumClass -> new MapBasedEnumDirectory(type));
    }

    @Override
    public Optional<E> parse(final CommandInput input, final S sender) {
        return Optional.ofNullable(converter.convertWith(input.getArgument(0), directory));
    }

    private class MapBasedEnumDirectory implements EnumDirectory<E> {
        private final Map<String, E> enumConstants = new HashMap<>();

        private MapBasedEnumDirectory(final Class<E> type) {
            for (final E enumConstant : type.getEnumConstants())
                enumConstants.put(enumConstant.name(), enumConstant);
        }

        @Override
        public @Nullable E get(final String nameArgument) {
            return enumConstants.get(nameArgument);
        }
    }
}
