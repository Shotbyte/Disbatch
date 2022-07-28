package io.github.disbatch.command.builder;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandSender;

/**
 *
 */
public final class TabCompleters {
    private static final TabCompleter<?> EMPTY_TAB_COMPLETER = (sender, input) -> ImmutableList.of();

    private TabCompleters() {
        throw new AssertionError();
    }

    /**
     * @param <S>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <S extends CommandSender> TabCompleter<S> emptyTabCompleter() {
        return (TabCompleter<S>) EMPTY_TAB_COMPLETER;
    }
}
