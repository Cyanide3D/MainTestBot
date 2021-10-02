package ru.cyanide3d.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cyanide3d.bot.config.Configuration;
import ru.cyanide3d.bot.listeners.CommandClientManager;
import ru.cyanide3d.bot.listeners.MainEventListener;
import ru.cyanide3d.bot.listeners.SimpleEventListener;

public class Main {

    public static void main(String[] args) throws Exception {
        Configuration configuration = Configuration.getInstance();
        Logger logger = LoggerFactory.getLogger(Main.class);

        JDA jda = JDABuilder.createDefault(configuration.getToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();

        CommandClientManager commandClientManager = CommandClientManager.create(jda);
        jda.addEventListener(new SimpleEventListener(), new MainEventListener());

        logger.info("Successful initialization");
    }

}
