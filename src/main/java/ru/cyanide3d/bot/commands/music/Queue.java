package ru.cyanide3d.bot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import ru.cyanide3d.bot.config.music.PlayerManager;
import ru.cyanide3d.bot.utils.MusicUtil;

import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class Queue extends Command {

    public Queue() {
        this.name = "queue";
    }

    @Override
    protected void execute(CommandEvent event) {
        MusicUtil.isCanUseMusicCommand(event);
        BlockingQueue<AudioTrack> queue = PlayerManager.getInstance().getGuildMusicManager(event.getGuild()).scheduler.getQueue();

        if (queue.isEmpty()) {
            event.reply("Очередь пуста.");
            return;
        }

        String collect = queue.stream().limit(10)
                .map(track -> track.getInfo().title + " : **" + track.getInfo().length / 1000 / 60 + " мин.**").collect(Collectors.joining("\n"));

        event.reply(new EmbedBuilder()
                .setThumbnail("https://media.tenor.com/images/8729229b46bf9e2756692cfeff94ae64/tenor.gif")
                .addField("**Очередь:(Первые 11 треков)**", collect, false)
                .build());
    }
}
