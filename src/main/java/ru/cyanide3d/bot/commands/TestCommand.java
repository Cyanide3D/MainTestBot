package ru.cyanide3d.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import ru.cyanide3d.bot.config.Permission;
import ru.cyanide3d.bot.service.PermissionService;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "sc";
        this.hidden = true;

    }

    @Override
    protected void execute(CommandEvent event) {
        if (!event.getMember().getId().equals("534894366448156682")) {
            return;
        }

        System.out.println(PermissionService.getInstance().isAvailable(event.getMember(), Permission.USER, event.getGuild().getId()));
    }
}
