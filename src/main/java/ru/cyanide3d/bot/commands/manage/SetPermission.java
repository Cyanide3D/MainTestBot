package ru.cyanide3d.bot.commands.manage;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Role;
import ru.cyanide3d.bot.config.Permission;
import ru.cyanide3d.bot.exceptions.AccessDeniedException;
import ru.cyanide3d.bot.service.PermissionService;

import java.util.List;

public class SetPermission extends Command {

    private final PermissionService permissionService;

    public SetPermission() {
        this.permissionService = PermissionService.getInstance();
        this.name = "set";
    }

    @Override
    protected void execute(CommandEvent event) {
        if (permissionService.isAvailable(event.getMember(), Permission.ADMIN, event.getGuild().getId())) {
            final List<Role> mentionedRoles = event.getMessage().getMentionedRoles();
            final String[] args = event.getArgs().split(" ");

            if (args.length != 2 || mentionedRoles.isEmpty()) {
                return;
            }

            try {
                permissionService.setPermission(event.getGuild().getId(), Permission.valueOf(args[0].toUpperCase()), mentionedRoles.get(0).getId());
            } catch (Exception e) {
                event.reply("Не правильно указаны полномочия.");
            }
        } else {
            throw new AccessDeniedException(event.getTextChannel());
        }
    }

}
