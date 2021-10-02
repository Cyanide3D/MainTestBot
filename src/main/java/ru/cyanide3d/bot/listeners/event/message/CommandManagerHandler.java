package ru.cyanide3d.bot.listeners.event.message;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import ru.cyanide3d.bot.config.Configuration;
import ru.cyanide3d.bot.listeners.CommandClientManager;

import java.util.List;

public class CommandManagerHandler implements GetMessageEvent {

    private final List<Character> AVAILABLE_PREFIXES;

    public CommandManagerHandler() {
        this.AVAILABLE_PREFIXES = Configuration.getInstance().getAvailablePrefixes();
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        char prefix = event.getMessage().getContentRaw().charAt(0);

        if (AVAILABLE_PREFIXES.contains(prefix)) {
            CommandClientManager commandClientManager = CommandClientManager.getInstance();
            commandClientManager.regCommandClient(event.getGuild().getId());
        }
    }

}
