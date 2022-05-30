package com.github.commandant.command.parameter;

import com.github.commandant.command.Command;
import com.github.commandant.command.parameter.model.SenderIndependentParameter;
import org.bukkit.command.CommandSender;

import java.util.Map;

/**
 *
 */
public final class SubcommandParameter extends SenderIndependentParameter<Command<CommandSender>> {
    private Map<String, SubcommandContainer> subcommands;
    private String[] args;

    public SubcommandParameter(final String label) {
        super(label);
    }

    @Override
    public boolean canParse(final String[] args) {
        return subcommands.containsKey(args[0]);
    }

    @Override
    protected Command<CommandSender> parse(final String[] args) {
        this.args = args;
        return subcommands.get(args[0]);
    }

    @Override
    public int getSize() {
        return 1;
    }

    void setSubcommands(final Map<String, SubcommandContainer> subcommands) {
        this.subcommands = subcommands;
    }

    String[] getLastParsedArgs() {
        return args;
    }
}
