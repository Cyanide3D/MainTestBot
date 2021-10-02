package ru.cyanide3d.bot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import org.apache.commons.lang3.tuple.Pair;
import ru.cyanide3d.bot.config.music.GuildMusicManager;
import ru.cyanide3d.bot.config.music.PlayerManager;
import ru.cyanide3d.bot.utils.MusicUtil;

import java.awt.*;
import java.util.Optional;

public class Skip extends Command {

    public Skip() {
        this.name = "skip";
        this.aliases = new String[]{"s"};
    }

    @Override
    protected void execute(CommandEvent event) {
        MusicUtil.isCanUseMusicCommand(event);

        Pair<AudioTrack, AudioTrack> tracks = MusicUtil.skip(event.getGuild());

        Optional.ofNullable(tracks.getLeft()).ifPresentOrElse(
                sq -> ifNotEmptyQueue(event, tracks.getLeft(), tracks.getRight()),
                () -> ifEmptyQueue(event)
        );
    }

    private void ifEmptyQueue(CommandEvent event) {
        event.reply(new EmbedBuilder()
                .setDescription(":stop_sign: Плейлист пуст!")
                .build());
    }

    private void ifNotEmptyQueue(CommandEvent event, AudioTrack skippedTrack, AudioTrack newTrack) {
        event.reply(new EmbedBuilder()
                .setDescription(":white_check_mark:Трек успешно сменён!:white_check_mark:")
                .setThumbnail("https://media.tenor.com/images/8729229b46bf9e2756692cfeff94ae64/tenor.gif")
                .addField(":o:Старый трек:o:", skippedTrack.getInfo().title, false)
                .addField(":next_track:Новый трек:next_track:", newTrack == null ? "Плейлист пуст." : newTrack.getInfo().title, false)
                .build());
    }
}
