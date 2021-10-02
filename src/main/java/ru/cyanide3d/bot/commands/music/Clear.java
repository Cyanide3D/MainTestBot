package ru.cyanide3d.bot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import ru.cyanide3d.bot.config.music.PlayerManager;
import ru.cyanide3d.bot.utils.MusicUtil;

public class Clear extends Command {

    public Clear() {
        this.name = "clear";
    }

    @Override
    protected void execute(CommandEvent event) {
        MusicUtil.isCanUseMusicCommand(event);

        PlayerManager.getInstance().getGuildMusicManager(event.getGuild()).scheduler.clearQueue();
        event.reply("Очередь очищена.");
    }
}
