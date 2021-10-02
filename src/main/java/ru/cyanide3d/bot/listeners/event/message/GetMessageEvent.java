package ru.cyanide3d.bot.listeners.event.message;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface GetMessageEvent {

    void execute(GuildMessageReceivedEvent event);

}
