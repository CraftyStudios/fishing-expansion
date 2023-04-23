package me.craftystudios.fishing.Commands;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.craftystudios.fishing.Main;
import me.craftystudios.fishing.Functions.Festival;

public class fishingexpansion implements CommandExecutor, TabCompleter {
    private Main plugin;
    private Festival festival;
    public fishingexpansion(Main plugin, Festival festival) {
        this.plugin = plugin;
        this.festival = festival;
    }
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) { // First argument
            completions.add("reload");
            completions.add("festival");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("festival")) { // Second argument for "festival"
            completions.add("start");
            completions.add("end");
        }

        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("fishing-expansion") && args.length == 0) {
        sender.sendMessage(ChatColor.GREEN + "Fishing Expansion v" + plugin.getDescription().getVersion() + " by Crafty Studios");
        sender.sendMessage(ChatColor.GREEN + "Use /fishing-expansion reload to reload the configuration");
        sender.sendMessage(ChatColor.GREEN + "Use /fishing-expansion festival start to start a festival");
        sender.sendMessage(ChatColor.GREEN + "Use /fishing-expansion festival stop to stop a festival");
        return true;
    } else
    if (command.getName().equalsIgnoreCase("fishing-expansion") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
        if(sender.hasPermission("fishingexpansion.reload")) {
        plugin.saveConfig();
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Fishing Expansion configuration reloaded!");
        return true;
        }
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
        }
        else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to do that!");
            return true;
        }
    }
    return false;
}


}
