package com.github.commandant.parameter.model;

import com.github.commandant.parameter.ArgumentQueue;
import com.github.commandant.parameter.ArgumentSelection;
import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 *
 */
public class WorldParameter extends SenderIndependentParameter<World> {
    public WorldParameter(final String label) {
        super(label);
    }

    @Override
    public boolean canParse(final ArgumentSelection selection) {
        return getWorld(selection.firstArgument()) != null;
    }

    @Override
    protected World parse(final ArgumentQueue queue) {
        return getWorld(queue.nextArgument());
    }

    private World getWorld(final String argument) {
        return Bukkit.getWorld(argument);
    }

    @Override
    public int getSize() {
        return 1;
    }
}
