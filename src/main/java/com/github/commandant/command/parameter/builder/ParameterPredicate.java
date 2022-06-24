package com.github.commandant.command.parameter.builder;

/**
 *
 */
public interface ParameterPredicate {
    boolean canParse(String[] args);
}
