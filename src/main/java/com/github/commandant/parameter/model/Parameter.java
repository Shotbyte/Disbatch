package com.github.commandant.parameter.model;

import com.github.commandant.parameter.ArgumentQueue;
import com.github.commandant.parameter.ArgumentSelection;
import org.bukkit.command.CommandSender;

/**
 * @param <K>
 * @param <V>
 */
public interface Parameter<K extends CommandSender, V> {

    /**
     * @param selection
     * @return
     */
    boolean canParse(ArgumentSelection selection);

    /**
     * @param queue
     * @param sender
     * @return
     */
    V parse(ArgumentQueue queue, K sender);

    /**
     * @return
     */
    String getLabel();

    /**
     * @return
     */
    int getSize();
}
