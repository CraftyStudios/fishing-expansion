package me.craftystudios.fishing.Functions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.craftystudios.fishing.Main;
import net.md_5.bungee.api.ChatColor;

public class Festival {
    private boolean festivalActive = false;
    private double rareChanceMultiplier = 1.0;
    private fish rc;
    private Main plugin;
    public Festival(fish rc, Main plugin) {
        this.rc = rc;
        this.plugin = plugin;

    }

    public void startFestival() {
        festivalActive = true;
        rareChanceMultiplier = plugin.getConfig().getInt("festivalmultiplyer");

        

        // Loop through all online players and set their rare chance
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN + "Festival started! (x" + rareChanceMultiplier + " rare chance)");
            double currentRareChance = rc.getRareChance(player);
            double newRareChance = currentRareChance * rareChanceMultiplier;
            rc.setRareChance(player, newRareChance);
        }
    }

    public void endFestival() {
        festivalActive = false;

        // Loop through all online players and reset their rare chance to default
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN + "Festival ended! All rare chances reset to default");
            rc.setRareChance(player, 1.0);
        }
    }

    public boolean isFestivalActive() {
        return festivalActive;
    }

    public double getRareChanceMultiplier() {
        return rareChanceMultiplier;
    }

    public void setRareChanceMultiplier(double rareChanceMultiplier) {
        this.rareChanceMultiplier = rareChanceMultiplier;
    }
    public void startFestivalScheduler() {
    int interval = plugin.getConfig().getInt("festival_interval") * 1200; // Convert seconds to ticks
    int duration = plugin.getConfig().getInt("festival_duration") * 1200; // Convert seconds to ticks

    Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
        @Override
        public void run() {
            startFestival();
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    endFestival();
                }
            }, duration);
        }
    }, 0, interval);   
}
}
