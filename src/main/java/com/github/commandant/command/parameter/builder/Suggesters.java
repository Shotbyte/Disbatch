package com.github.commandant.command.parameter.builder;

import com.github.commandant.command.builder.TabCompletions;
import lombok.experimental.UtilityClass;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 *
 */
@UtilityClass
public class Suggesters {
    private final Suggester<?> EMPTY_SUGGESTER = (sender, boundedArgs) -> TabCompletions.emptyList();

    /**
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends CommandSender> Suggester<T> emptySuggester() {
        return (Suggester<T>) EMPTY_SUGGESTER;
    }

    /**
     * @param collection
     * @param <T>
     * @return
     */
    public static <T extends CommandSender> Suggester<T> of(@NotNull final Collection<String> collection) {
        return (sender, boundedArgs) -> collection;
    }

    /**
     * @param suggester
     * @param <T>
     * @return
     */
    public static <T extends CommandSender> Suggester<T> forFirstArgument(@NotNull final Suggester<T> suggester) {
        return (sender, boundedArgs) -> boundedArgs.length <= 1
                ? suggester.getSuggestions(sender, boundedArgs)
                : TabCompletions.emptyList();
    }
}
