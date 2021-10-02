package ru.cyanide3d.bot.exceptions;

import net.dv8tion.jda.api.entities.TextChannel;

public class AccessDeniedException extends RuntimeException {

    private TextChannel channel;

    public AccessDeniedException(TextChannel channel) {
        this.channel = channel;
    }

    public TextChannel getChannel() {
        return channel;
    }
}
