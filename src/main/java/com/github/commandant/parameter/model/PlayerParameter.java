package com.github.commandant.parameter.model;

import com.github.commandant.parameter.ArgumentQueue;
import com.github.commandant.parameter.ArgumentSelection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 */
public class PlayerParameter extends SenderIndependentParameter<Player> {
    public PlayerParameter(final String label) {
        super(label);
    }

    @Override
    public boolean canParse(final ArgumentSelection selection) {
        return getPlayer(selection.firstArgument()) != null;
    }

    @Override
    protected Player parse(final ArgumentQueue queue) {
        return getPlayer(queue.nextArgument());
    }

    private Player getPlayer(final String argument) {
        return Bukkit.getPlayer(argument);
    }

    @Override
    public int getSize() {
        return 1;
    }
}
