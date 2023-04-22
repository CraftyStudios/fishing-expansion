package me.craftystudios.fishing.Commands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.craftystudios.fishing.Main;
import me.craftystudios.fishing.Functions.Festival;

public class fishingexpansion implements CommandExecutor{
    private Main plugin;
    private Festival festival;
    public fishingexpansion(Main plugin, Festival festival) {
        this.plugin = plugin;
        this.festival = festival;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("fishing-expansion") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
        plugin.saveConfig();
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Fishing Expansion configuration reloaded!");
        return true;
    } else if (command.getName().equalsIgnoreCase("fishing-expansion") && args.length > 0 && args[0].equalsIgnoreCase("festival")) {
        // Check if the player has permission to start a festival
        // then check if the input is start or stop
        if (sender.hasPermission("fishingexpansion.festival")) {
            if (args.length > 1 && args[1].equalsIgnoreCase("start")) {
                // Check if a festival is already active
                if (festival.isFestivalActive()) {
                    sender.sendMessage(ChatColor.RED + "A festival is already active!");
                    return true;
                }
                festival.startFestival();
                sender.sendMessage(ChatColor.GREEN + "Festival started!");
                return true;
            } else if (args.length > 1 && args[1].equalsIgnoreCase("stop")) {
                // Check if a festival is already active
                if (!festival.isFestivalActive()) {
                    sender.sendMessage(ChatColor.RED + "There is no festival active!");
                    return true;
                }
                festival.endFestival();
                sender.sendMessage(ChatColor.GREEN + "Festival ended!");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid arguments!");
                return false;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to do that!");
            return true;
        }
    }
    return false;
}


}
