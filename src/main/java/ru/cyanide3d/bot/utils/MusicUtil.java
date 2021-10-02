package ru.cyanide3d.bot.utils;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.apache.commons.lang3.tuple.Pair;
import ru.cyanide3d.bot.config.music.GuildMusicManager;
import ru.cyanide3d.bot.config.music.PlayerManager;
import ru.cyanide3d.bot.exceptions.DeniedAccessToJoinException;
import ru.cyanide3d.bot.exceptions.MusicUnsupportedActionException;
import ru.cyanide3d.bot.exceptions.UserIsAbsentInChannelException;

public class MusicUtil {

    private static final PlayerManager playerManager;

    static {
        playerManager = PlayerManager.getInstance();
    }

    public static void joinBotToChannel(CommandEvent event) {
        AudioManager audioManager = event.getGuild().getAudioManager();
        isUserInVoiceChannel(event);
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();
        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        isHavePermissionToJoin(event, voiceChannel);
        audioManager.openAudioConnection(voiceChannel);
    }

    public static void leave(Guild guild) {
        guild.getAudioManager().closeAudioConnection();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild);
        musicManager.player.stopTrack();
        musicManager.scheduler.clearQueue();
    }

    public static Pair<AudioTrack, AudioTrack> skip(Guild guild) {
        GuildMusicManager guildMusicManager = playerManager.getGuildMusicManager(guild);
        AudioTrack skippedTrack = guildMusicManager.player.getPlayingTrack();
        guildMusicManager.nextTrack();
        AudioTrack newTrack = guildMusicManager.player.getPlayingTrack();

        return Pair.of(skippedTrack, newTrack);
    }

    public static void stop(Guild guild) {
        GuildMusicManager guildMusicManager = playerManager.getGuildMusicManager(guild);
        guildMusicManager.player.stopTrack();
        guildMusicManager.scheduler.clearQueue();
    }

    public static void play(CommandEvent event) {
        playerManager.loadAndPlay(event.getTextChannel(), YouTubeApi.getVideoUrlFromQuery(event.getArgs()));
        playerManager.getGuildMusicManager(event.getGuild()).player.setVolume(15);
    }

    private static void isUserInVoiceChannel(CommandEvent event) {
        if (!event.getMember().getVoiceState().inVoiceChannel()) {
            throw new UserIsAbsentInChannelException();
        }
    }

    private static void isHavePermissionToJoin(CommandEvent event, VoiceChannel voiceChannel) {
        Member selfMember = event.getGuild().getSelfMember();

        if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
            throw new DeniedAccessToJoinException();
        }
    }
    
    public static void isCanUseMusicCommand(CommandEvent event) {
        if (!event.getGuild().getAudioManager().isConnected()) {
            event.reply("Бота нет в голосовом канале!");
            throw new MusicUnsupportedActionException();
        }
        if (!event.getGuild().getAudioManager().getConnectedChannel().getMembers().contains(event.getMember())) {
            event.reply("Для выполнения команды необходимо находится в одном канале с ботом!");
            throw new MusicUnsupportedActionException();
        }
    }
}
