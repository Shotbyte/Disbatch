package io.github.disbatch.command;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Holds various necessary and optional data that should be associated with a {@link Command} upon registration, such as
 * its label (required) and available aliases (optional).
 */
public final class CommandDescriptor {
    private final List<String> aliases = new LinkedList<>();
    private final String description;
    private final String label;
    private final String validSenderMessage;

    private CommandDescriptor(final @NotNull String description, final @NotNull String label, final @NotNull String[] aliases, final @NotNull String validSenderMessage) {
        this.description = description;
        this.label = label;
        this.validSenderMessage = validSenderMessage;
        this.aliases.addAll(Arrays.asList(aliases));
    }

    /**
     * @param label
     * @return
     */
    public static CommandDescriptor label(final @NotNull String label) {
        return builder().label(label).build();
    }

    /**
     * Creates a {@link Builder}.
     *
     * @return the created descriptor builder
     * @see CommandDescriptor#label(String)
     */
    public static Builder builder() {
        return new Builder();
    }

    public String getDescription() {
        return description;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getValidSenderMessage() {
        return validSenderMessage;
    }

    /**
     * Serves as a flexible solution for creating a new {@link CommandDescriptor}.
     */
    public static final class Builder {
        private String description = StringUtils.EMPTY;
        private String label;
        private String[] aliases;
        private String validSenderMessage = StringUtils.EMPTY;

        private Builder() {
        }

        /**
         * Sets the description, which is optional, for use by the created descriptor for when a {@link Command}
         * is registered with it.
         *
         * @param description the description that should be used for this builder
         * @return the corresponding builder
         */
        public Builder description(final @NotNull String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the command label for use by the created descriptor for when a {@link Command} is registered with it.
         *
         * @param label the label that should be used for this builder
         * @return the corresponding builder
         */
        public Builder label(final @NotNull String label) {
            this.label = label;
            return this;
        }

        /**
         * Sets the command label aliases, which is optional, for use by the created descriptor for when a {@link Command}
         * is registered with it.
         *
         * @param aliases the aliases that should be used for this builder
         * @return the corresponding builder
         */
        public Builder aliases(final @NotNull String... aliases) {
            this.aliases = aliases;
            return this;
        }

        /**
         * @param validSenderMessage
         * @return
         */
        public Builder validSenderMessage(final @NotNull String validSenderMessage) {
            this.validSenderMessage = validSenderMessage;
            return this;
        }

        /**
         * Creates a new {@link CommandDescriptor}.
         *
         * @return the created descriptor
         */
        public @NotNull CommandDescriptor build() {
            Validate.notEmpty(label, "Command label cannot be empty");

            final String[] aliases = this.aliases == null ? ArrayUtils.EMPTY_STRING_ARRAY : this.aliases;
            return new CommandDescriptor(description, label, aliases, validSenderMessage);
        }
    }
}
