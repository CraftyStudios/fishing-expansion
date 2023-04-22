package me.craftystudios.fishing.Functions;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import me.craftystudios.fishing.Main;

public class fish implements Listener {
    
    private Main plugin;
    public fish(Main plugin) {
        this.plugin = plugin;
    }
    public HashMap<Player, Double> rarechances = new HashMap<Player, Double>();
    private double rngNumber() {
        return Math.random();
    }
    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        float rng = 0.5f;

        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
                if (Math.random() < rng) {
                    // drop a fish
                    ItemStack fish = new ItemStack(Material.COD, 1);
                    player.getInventory().addItem(fish);
                }
                //get the chance from the config
                ConfigurationSection section = plugin.getConfig().getConfigurationSection("items");

                if (section != null) {
                    for (String key : section.getKeys(false)) {
                        double chance = plugin.getConfig().getDouble("items." + key + ".chance");
                        String name = plugin.getConfig().getString("items." + key + ".name");
                        if (rngNumber() > chance * rarechances.getOrDefault(player, 1.0)) {
                            // drop the item
                            ItemStack item = new ItemStack(Material.getMaterial(key), 1);
                            player.getInventory().addItem(item);
                            if(rngNumber() < plugin.getConfig().getDouble("superrarechance")) {
                                player.sendMessage("§dCRAZY RARE DROP! §7(§5" + name + "§7) (§5" + (int) chance * 100 + "%§7)");
                            } else if (rngNumber() < plugin.getConfig().getDouble("rarechance")) {
                            player.sendMessage("§dRARE DROP! §7(§5" + name + "§7) (§5" + (int) chance * 100 + "%§7)");
                            }
                            else {
                                player.sendMessage("§dRNG DROP! §7(§5" + name + "§7) (§5" + (int) chance * 100 + "%§7)");
                            }
                
                            // check for command and execute if it exists
                            if (section.contains(key + ".command")) {
                                String command = section.getString(key + ".command");
                                if (command != null && !command.isEmpty()) {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName() .replaceAll("&", "§")));
                                }
                            }
                        }
                    }
                }
            }
        }
    public void setRareChance(Player player, Double chance) {
        rarechances.put(player, chance);
    }
    public Double getRareChance(Player player) {
        return rarechances.get(player);
    }
}
