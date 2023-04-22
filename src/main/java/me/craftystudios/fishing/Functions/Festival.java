package me.craftystudios.fishing.Functions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.craftystudios.fishing.Main;

public class Festival {
    private boolean festivalActive = false;
    private double rareChanceMultiplier = 1.0;
    private fish rc;
    private Main plugin;
    public Festival(fish rc, Main plugin) {
        this.rc = rc;

    }

    public void startFestival() {
        festivalActive = true;
        rareChanceMultiplier = plugin.getConfig().getInt("festivalmultiplyer");

        // Loop through all online players and set their rare chance
        for (Player player : Bukkit.getOnlinePlayers()) {
            double currentRareChance = rc.getRareChance(player);
            double newRareChance = currentRareChance * rareChanceMultiplier;
            rc.setRareChance(player, newRareChance);
        }
    }

    public void endFestival() {
        festivalActive = false;

        // Loop through all online players and reset their rare chance to default
        for (Player player : Bukkit.getOnlinePlayers()) {
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
}
