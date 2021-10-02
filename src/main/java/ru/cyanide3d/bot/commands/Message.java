package ru.cyanide3d.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Message extends Command {

    public Message() {
        this.name = "message";
        this.aliases = new String[]{"msg"};
        this.arguments = "[Сообщение]";
        this.help = "Пишет сообщение от имени бота.";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply(event.getArgs());
        event.getMessage().delete().queue();
    }
}
