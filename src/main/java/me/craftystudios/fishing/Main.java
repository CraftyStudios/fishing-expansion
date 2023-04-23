package me.craftystudios.fishing;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.craftystudios.fishing.Commands.fishingexpansion;
import me.craftystudios.fishing.Functions.Festival;
import me.craftystudios.fishing.Functions.fish;
import me.craftystudios.fishing.utils.Logger;
import me.craftystudios.fishing.utils.Logger.LogLevel;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

      fish fishListener = new fish(this);
      Festival festival = new Festival(fishListener, this);
      getServer().getPluginManager().registerEvents(fishListener, this);
      getCommand("fishing-expansion").setExecutor(new fishingexpansion(this, festival));
      for (Player player : Bukkit.getOnlinePlayers()) {
        fishListener.setRareChance(player, 1.0);
        Logger.log(LogLevel.SUCCESS, player.getName() + " has been added to the rare chance list!");
    }
    festival.startFestivalScheduler();
    fishListener.setupUserRareChance();
    


      this.saveDefaultConfig();
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
      Logger.log(Logger.LogLevel.SUCCESS, "Loading Fishing Expansion...");
      Logger.log(Logger.LogLevel.SUCCESS, "Loaded!");
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
    }   

    @Override
    public void onDisable() {
      this.saveConfig();
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
      Logger.log(Logger.LogLevel.SUCCESS, "Unloading Fishing Expansion...");
      Logger.log(Logger.LogLevel.SUCCESS, "Unloaded!");
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
      this.saveConfig();
      }
    }