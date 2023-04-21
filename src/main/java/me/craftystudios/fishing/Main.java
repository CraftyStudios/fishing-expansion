package me.craftystudios.fishing;
import org.bukkit.plugin.java.JavaPlugin;

import me.craftystudios.fishing.Commands.fishingexpansion;
import me.craftystudios.fishing.Functions.fish;
import me.craftystudios.fishing.utils.Logger;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
      fish fishListener = new fish(this);
      getServer().getPluginManager().registerEvents(fishListener, this);
      getCommand("fishingexpansion").setExecutor(new fishingexpansion(this));


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