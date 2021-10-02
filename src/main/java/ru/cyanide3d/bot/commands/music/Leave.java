package ru.cyanide3d.bot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import ru.cyanide3d.bot.utils.MusicUtil;

public class Leave extends Command {

    public Leave() {
        this.name = "leave";
    }

    @Override
    protected void execute(CommandEvent event) {
        MusicUtil.isCanUseMusicCommand(event);

        MusicUtil.leave(event.getGuild());

        event.reply("Отключился от канала.");
    }
}
