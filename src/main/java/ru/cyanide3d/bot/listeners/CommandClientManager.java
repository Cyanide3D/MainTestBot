package ru.cyanide3d.bot.listeners;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cyanide3d.bot.commands.CalcElectric;
import ru.cyanide3d.bot.commands.Message;
import ru.cyanide3d.bot.commands.TestCommand;
import ru.cyanide3d.bot.commands.manage.Permission;
import ru.cyanide3d.bot.commands.manage.Prefix;
import ru.cyanide3d.bot.commands.music.*;
import ru.cyanide3d.bot.config.Configuration;
import ru.cyanide3d.bot.dto.GuildProperty;
import ru.cyanide3d.bot.service.GuildService;

import java.util.HashMap;
import java.util.Map;

public class CommandClientManager {

    private final JDA jda;
    private static CommandClientManager INSTANCE;
    private final Configuration configuration;
    private final Map<String, CommandClient> map;
    private final GuildService guildService;
    private CommandClient currentClient;
    private final Logger logger;

    public CommandClientManager(JDA jda) {
        this.guildService = GuildService.getInstance();
        this.configuration = Configuration.getInstance();
        this.jda = jda;
        map = new HashMap<>();
        this.logger = LoggerFactory.getLogger(CommandClientManager.class);
    }

    public void regCommandClient(String guildId) {
        updateListener(map.computeIfAbsent(guildId, k -> getClient(guildId)));
    }

    private void updateListener(String guildId) {
        updateListener(map.put(guildId, getClient(guildId)));
    }

    private synchronized void updateListener(CommandClient client) {
        if (currentClient != null) {
            jda.removeEventListener(currentClient);
        }

        jda.addEventListener(client);
        currentClient = client;
    }

    public CommandClient getClient(String guildId) {
        GuildProperty guildProperty = guildService.getGuildProperty(guildId);
        logger.info("Create command client for guild: " + guildId);

        return new CommandClientBuilder()
                .setOwnerId(configuration.getOwnerId())
                .setActivity(Activity.playing("with life"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setPrefix(guildProperty.getPrefix())
                .addCommands(new TestCommand(),
                        new Play(),
                        new Queue(),
                        new Skip(),
                        new Stop(),
                        new Clear(),
                        new Leave(),
                        new CalcElectric(),
                        new Prefix(),
                        new Permission(),
                        new Message())
                .useHelpBuilder(false)
                .build();
    }

    public void setPrefix(String prefix, String guildId) {
        guildService.setPrefix(prefix, guildId);
        updateListener(guildId);
    }

    public static CommandClientManager create(JDA jda) {
        if (INSTANCE == null) {
            INSTANCE = new CommandClientManager(jda);
        }

        return INSTANCE;
    }

    public static CommandClientManager getInstance() {
        if (INSTANCE == null) {
            throw new UnsupportedOperationException("Use create method to create instance.");
        }
        return INSTANCE;
    }

}
