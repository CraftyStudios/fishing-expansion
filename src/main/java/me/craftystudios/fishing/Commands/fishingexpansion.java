package me.craftystudios.fishing.Commands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.craftystudios.fishing.Main;

public class fishingexpansion implements CommandExecutor{
    private Main plugin;
    public fishingexpansion(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("fishing-expansion") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
        plugin.saveConfig();
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Fishing Expansion configuration reloaded!");
        return true;
    }
    return false;
}


}
