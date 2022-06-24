package com.github.commandant.command.parameter.builder;

import com.github.commandant.command.parameter.model.Parameter;
import org.bukkit.command.CommandSender;

/**
 * Acts in place of {@link Parameter#parse(String[], CommandSender)}
 */
public interface ParameterParser<K extends CommandSender, V> {

    /**
     * See {@link ParameterParser}.
     */
    V parse(String[] boundedArgs, K sender);
}
