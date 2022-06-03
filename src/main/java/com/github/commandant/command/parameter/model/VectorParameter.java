package com.github.commandant.command.parameter.model;

import org.bukkit.command.CommandSender;
import org.bukkit.util.Vector;

/**
 *
 */
public class VectorParameter extends NumericParameter<CommandSender, Vector> {
    public VectorParameter(final String xLabel, final String yLabel, final String zLabel) {
        super(xLabel, yLabel, zLabel);
    }

    @Override
    public Vector parse(final String[] args, final CommandSender sender) {
        return new Vector(parseNumber(args[0]), parseNumber(args[1]), parseNumber(args[2]));
    }
}
