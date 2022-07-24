package io.github.disbatch.command.builder;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 *
 */
public final class TabCompletions {
    private static final TabCompleter<?> EMPTY_TAB_COMPLETER = (sender, input) -> emptyList();

    private TabCompletions() {
        throw new AssertionError();
    }

    /**
     * @return
     */
    public static List<String> emptyList() {
        return ImmutableList.of();
    }

    /**
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends CommandSender> TabCompleter<T> emptyTabCompleter() {
        return (TabCompleter<T>) EMPTY_TAB_COMPLETER;
    }
}
