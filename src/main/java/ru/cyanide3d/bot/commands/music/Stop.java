package ru.cyanide3d.bot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import ru.cyanide3d.bot.utils.MusicUtil;

public class Stop extends Command {

    public Stop() {
        this.name = "stop";
    }

    @Override
    protected void execute(CommandEvent event) {
        MusicUtil.isCanUseMusicCommand(event);

        MusicUtil.stop(event.getGuild());

        event.reply("Бот остановлен.");
    }
}
