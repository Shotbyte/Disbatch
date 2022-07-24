package io.github.disbatch.command.parameter.builder;

import com.google.common.collect.Lists;
import io.github.disbatch.command.builder.TabCompletions;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 *
 */
public final class Suggesters {
    private static final Suggester<?> EMPTY_SUGGESTER = (sender, input) -> TabCompletions.emptyList();

    private Suggesters() {
        throw new AssertionError();
    }

    /**
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends CommandSender> Suggester<T> emptySuggester() {
        return (Suggester<T>) EMPTY_SUGGESTER;
    }

    /**
     * @param elements
     * @param <T>
     * @return
     */
    public static <T extends CommandSender> Suggester<T> of(final @NotNull String[] elements) {
        return of(Lists.newArrayList(elements));
    }

    /**
     * @param collection
     * @param <T>
     * @return
     */
    public static <T extends CommandSender> Suggester<T> of(final @NotNull Collection<String> collection) {
        return (sender, input) -> collection;
    }

    /**
     * @param suggester
     * @param <T>
     * @return
     */
    public static <T extends CommandSender> Suggester<T> forFirstArgument(final @NotNull Suggester<T> suggester) {
        return (sender, input) -> {
            final int length = input.getArgumentLength();
            return length == 1
                    ? suggester.getSuggestions(sender, input)
                    : TabCompletions.emptyList();
        };
    }
}
