package com.bedless.spawnplugin;

import co.aikar.commands.PaperCommandManager;
import com.bedless.spawnplugin.config.ConfigurationManager;
import com.bedless.spawnplugin.spawn.BuildCommand;
import com.bedless.spawnplugin.spawn.ReloadConfigCommand;
import com.bedless.spawnplugin.spawn.SetSpawnCommand;
import com.bedless.spawnplugin.spawn.SpawnCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static com.bedless.spawnplugin.config.ConfigurationOption.*;
import static com.bedless.spawnplugin.spawn.BuildCommand.playersInBuildMode;

public final class SpawnPlugin extends JavaPlugin implements Listener {
    private static SpawnPlugin INSTANCE;

    public static SpawnPlugin getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        ConfigurationManager.getInstance();
        registerCommands();
        registerEvents();
        String version1 = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].split("_")[1];
        if(version1.equals(8)){
            String line1 = ChatColor.GREEN + "===================";
            Bukkit.getConsoleSender().sendMessage(line1);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Custom Spawn Plugin");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
            Bukkit.getConsoleSender().sendMessage("Running on " + Bukkit.getBukkitVersion());
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Warning Multiverse and other Plugins may override this Plugin (Like sending People to spawn)");
            Bukkit.getConsoleSender().sendMessage(line1);
        } else if(version1.equals(9-18)){
            String line1 = ChatColor.GREEN + "===================";
            Bukkit.getConsoleSender().sendMessage(line1);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Custom Spawn Plugin");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
            Bukkit.getConsoleSender().sendMessage("Running on " + Bukkit.getBukkitVersion());
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "This is an Unsupported Version! It  may not load correctly!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Warning Multiverse and other Plugins may override this Plugin (Like sending People to spawn)");
            Bukkit.getConsoleSender().sendMessage(line1);
        }else if(version1.equals(7)){
            String line1 = ChatColor.GREEN + "===================";
            Bukkit.getConsoleSender().sendMessage(line1);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Custom Spawn Plugin");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
            Bukkit.getConsoleSender().sendMessage("Running on " + Bukkit.getBukkitVersion());
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "This is an Unsupported Version! It  may not load correctly!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Warning Multiverse and other Plugins may override this Plugin (Like sending People to spawn)");
            Bukkit.getConsoleSender().sendMessage(line1);
        }
    }

    public void registerCommands() {
        PaperCommandManager cdm = new PaperCommandManager(this);
        cdm.registerCommand(new BuildCommand());
        cdm.registerCommand(new SetSpawnCommand());
        cdm.registerCommand(new SpawnCommand());
        cdm.registerCommand(new ReloadConfigCommand());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        ConfigurationManager.getInstance().saveConfiguration();
        Bukkit.getConsoleSender().sendMessage("Disabled");
        String line2 = ChatColor.RED + "===================";
        Bukkit.getConsoleSender().sendMessage(line2);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Custom Spawn Plugin");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Disabled");
        Bukkit.getConsoleSender().sendMessage(line2);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (ConfigurationManager.getInstance().getBoolean(TELEPORT_TO_SPAWN_JOIN)) {
            ConfigurationManager.getInstance().sendToSpawn(player);
            player.setHealth(20);
            player.setFoodLevel(20);
        }
        if (ConfigurationManager.getInstance().getBoolean(CLEAR_INV_JOIN)) {
            player.getInventory().clear();
        }
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player player = e.getEntity();
        if (ConfigurationManager.getInstance().getBoolean(SEND_TO_SPAWN_DEATH)) {
            ConfigurationManager.getInstance().sendToSpawn(player);
            player.setHealth(20);
            player.setFoodLevel(20);
        }
        if (ConfigurationManager.getInstance().getBoolean(CLEAR_INV_DEATH)) {
            player.getInventory().clear();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        //Removes player off of Build List on Logout
        playersInBuildMode.remove(e.getPlayer());
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        //e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (ConfigurationManager.getInstance().getBoolean(PLAYER_IMMUNITY)) {
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_INTERACT)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
        if (playersInBuildMode.contains(e.getPlayer())) {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onFireTickEvent(BlockBurnEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_IMMUNITY)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onTntExplodeEvent(ExplosionPrimeEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_IMMUNITY)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_HUNGER)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onPlayerBreakEvent(BlockBreakEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(BREAK_BLOCKS)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
        if (playersInBuildMode.contains(e.getPlayer())) {
            e.setCancelled(false);
        }
    }
    @EventHandler
    public void onPlayerPlaceEvent(BlockPlaceEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLACE_BLOCKS)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
        if (playersInBuildMode.contains(e.getPlayer())) {
            e.setCancelled(false);
        }
    }

}
