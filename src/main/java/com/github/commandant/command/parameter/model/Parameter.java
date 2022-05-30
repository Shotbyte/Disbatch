package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;

/**
 * @param <K>
 * @param <V>
 */
public interface Parameter<K extends CommandSender, V> {

    /**
     * @param args
     * @return
     */
    boolean canParse(String[] args);

    /**
     * @param args
     * @param sender
     * @return
     */
    V parse(String[] args, K sender);

    /**
     * @return
     */
    String[] getLabels();

    /**
     * @return
     */
    int getSize();
}
