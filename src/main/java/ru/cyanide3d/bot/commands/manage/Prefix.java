package ru.cyanide3d.bot.commands.manage;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import ru.cyanide3d.bot.config.Configuration;
import ru.cyanide3d.bot.config.Permission;
import ru.cyanide3d.bot.exceptions.AccessDeniedException;
import ru.cyanide3d.bot.listeners.CommandClientManager;
import ru.cyanide3d.bot.service.PermissionService;

import java.util.List;

public class Prefix extends Command {

    public Prefix() {
        this.name = "prefix";
    }

    @Override
    protected void execute(CommandEvent event) {
        if (PermissionService.getInstance().isAvailable(event.getMember(), Permission.ADMIN, event.getGuild().getId())) {
            List<Character> availablePrefixes = Configuration.getInstance().getAvailablePrefixes();
            if (!availablePrefixes.contains(event.getArgs().charAt(0)) || event.getArgs().split("").length != 1) {
                event.reply("Недопустимый префикс.");
                return;
            }

            CommandClientManager commandClientManager = CommandClientManager.getInstance();
            commandClientManager.setPrefix(event.getArgs(), event.getGuild().getId());
            event.reply("Префикс успешно установлен.");
        } else {
            throw new AccessDeniedException(event.getTextChannel());
        }
    }

}