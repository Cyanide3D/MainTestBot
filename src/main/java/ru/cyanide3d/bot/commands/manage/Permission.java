package ru.cyanide3d.bot.commands.manage;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Permission extends Command {

    public Permission() {
        this.name = "permission";
        this.children = new Command[]{new SetPermission()};
    }

    @Override
    protected void execute(CommandEvent event) {
    }
}
