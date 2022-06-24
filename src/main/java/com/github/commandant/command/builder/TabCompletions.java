package com.github.commandant.command.builder;

import com.google.common.collect.ImmutableList;
import lombok.experimental.UtilityClass;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 *
 */
@UtilityClass
public class TabCompletions {
    private final TabCompleter<?> EMPTY_TAB_COMPLETER = (sender, args) -> emptyList();

    /**
     * @return
     */
    public List<String> emptyList() {
        return ImmutableList.of();
    }

    /**
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends CommandSender> TabCompleter<T> emptyTabCompleter() {
        return (TabCompleter<T>) EMPTY_TAB_COMPLETER;
    }
}
