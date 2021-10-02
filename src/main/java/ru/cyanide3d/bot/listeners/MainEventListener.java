package ru.cyanide3d.bot.listeners;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cyanide3d.bot.listeners.event.message.CommandManagerHandler;
import ru.cyanide3d.bot.listeners.event.message.GetMessageEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class MainEventListener extends ListenerAdapter {

    private final Logger logger = LoggerFactory.getLogger(MainEventListener.class);

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        final List<GetMessageEvent> list = List.of(new CommandManagerHandler());

        list.forEach(handler -> {
            try {
                handler.execute(event);
            } catch (Exception ignore) {
            }
        });
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        logger.info("Working on " + event.getGuildAvailableCount() + " servers.");
    }

//    @Override
//    public void onException(@Nonnull ExceptionEvent event) {
//        if (event.getCause() instanceof MusicUnsupportedActionException) {
//            return;
//        } else if (event.getCause() instanceof AccessDeniedException) {
//            AccessDeniedException e = (AccessDeniedException) event.getCause();
//            e.getChannel().sendMessage("Нет прав для использования данной функции.").queue();
//        } else {
//            super.onException(event);
//        }
//    }

}
