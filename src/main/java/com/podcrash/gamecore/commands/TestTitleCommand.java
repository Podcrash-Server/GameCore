package com.podcrash.gamecore.commands;

import com.podcrash.gamecore.title.TitleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestTitleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args)
            stringBuilder.append(arg).append(" ");
        TitleManager titleManager = new TitleManager();
        titleManager.spawnTitle(stringBuilder.toString().trim(), (Player) sender);
        return true;
    }
}
