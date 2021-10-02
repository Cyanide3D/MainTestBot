package ru.cyanide3d.bot.config.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AudioLoadResultHandlerImpl implements AudioLoadResultHandler {

    private final TextChannel channel;
    private final GuildMusicManager musicManager;
    private final String trackUrl;

    public AudioLoadResultHandlerImpl(TextChannel channel, GuildMusicManager musicManager, String trackUrl) {
        this.channel = channel;
        this.musicManager = musicManager;
        this.trackUrl = trackUrl;
    }

    @Override
    public void trackLoaded(AudioTrack track) {
        channel.sendMessage(playMessage(
                ":musical_keyboard: Трек добавлен в очередь :musical_keyboard:",
                ":musical_note:" + track.getInfo().title + ":musical_note:",
                "Длительность: " + audioTrackLength(track) + " мин.",
                "<" + trackUrl + ">"
        )).queue();

        play(musicManager, track);
    }

    private String audioTrackLength(AudioTrack track) {
        String seconds = ((track.getInfo().length / 1000) % 60) + "";
        String fullSeconds = seconds.length() < 2 ? "0" + seconds : seconds;
        return track.getInfo().length / 1000 / 60 + ":" + fullSeconds;
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        if (playlist.getTracks().size() > 40) {
            channel.sendMessage(errorMessage("Слишком много песен в плейлисте.")).queue();
        } else {
            channel.sendMessage(playMessage(
                    ":musical_keyboard: Плейлист добавлен в очередь :musical_keyboard:",
                    ":musical_note: **Название:** " + playlist.getName() + " :musical_note:",
                    "Треков: " + playlist.getTracks().size(),
                    "**Первый трек:** " + playlist.getTracks().get(0).getInfo().title
            )).queue();

            play(musicManager, playlist.getTracks());
        }
    }

    private MessageEmbed playMessage(String firstTitle, String firstText, String secondTitle, String secondText) {
        return new EmbedBuilder()
                .setThumbnail("https://media.tenor.com/images/8729229b46bf9e2756692cfeff94ae64/tenor.gif")
                .addField(firstTitle, firstText, false)
                .addField(secondTitle, secondText, false)
                .build();
    }

    @Override
    public void noMatches() {
        channel.sendMessage(errorMessage("Ничего не найдено по запросу.")).queue();
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        channel.sendMessage(errorMessage("Ошибка загрузки музыки.")).queue();
    }

    @NotNull
    private MessageEmbed errorMessage(String message) {
        return new EmbedBuilder()
                .setDescription(":no_entry_sign: " + message)
                .build();
    }

    private void play(GuildMusicManager musicManager, AudioTrack track) {
        musicManager.scheduler.queue(track);
    }

    private void play(GuildMusicManager musicManager, List<AudioTrack> tracks) {
        musicManager.scheduler.queue(tracks);
    }
}
