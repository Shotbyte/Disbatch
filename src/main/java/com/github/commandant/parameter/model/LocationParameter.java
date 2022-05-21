package com.github.commandant.parameter.model;

import com.github.commandant.parameter.ArgumentQueue;
import com.github.commandant.parameter.ArgumentSelection;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationParameter extends AbstractParameter<Player, Location> {
    public LocationParameter(final String label) {
        super(label);
    }

    @Override
    public boolean canParse(final ArgumentSelection selection) {
        return false;
    }

    @Override
    public Location parse(final ArgumentQueue queue, final Player sender) {
        return new Location(sender.getWorld(),
                parseDouble(queue.nextArgument()),
                parseDouble(queue.nextArgument()),
                parseDouble(queue.nextArgument()));
    }

    private double parseDouble(final String argument) {
        return Double.parseDouble(argument);
    }

    @Override
    public int getSize() {
        return 3;
    }
}
