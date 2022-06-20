package com.github.commandant.command.decorator;

/**
 * @param <T>
 */
public interface NoArgumentsExecutor<T> {

    /**
     * @param sender
     * @param commandLabel
     */
    void execute(T sender, String commandLabel);
}
