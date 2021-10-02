package ru.cyanide3d.bot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import ru.cyanide3d.bot.exceptions.DeniedAccessToJoinException;
import ru.cyanide3d.bot.exceptions.UserIsAbsentInChannelException;
import ru.cyanide3d.bot.utils.MusicUtil;

public class Play extends Command {

    public Play() {
        this.name = "play";
        this.aliases = new String[]{"p"};
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().isEmpty()) {
            event.reply("Необходимо указать название песни или ссылку на видеоролик.");
            return;
        }

        try {
            MusicUtil.joinBotToChannel(event);
            MusicUtil.play(event);
        } catch (UserIsAbsentInChannelException e) {
            event.reply("Необходимо находиться в голосовом канале для этого!");
        } catch (DeniedAccessToJoinException e) {
            event.reply("Нет доступа к каналу");
        }
    }
}
