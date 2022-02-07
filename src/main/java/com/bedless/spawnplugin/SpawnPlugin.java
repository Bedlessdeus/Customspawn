package com.bedless.spawnplugin;

import co.aikar.commands.PaperCommandManager;
import com.bedless.spawnplugin.config.ConfigurationManager;
import com.bedless.spawnplugin.spawn.BuildCommand;
import com.bedless.spawnplugin.spawn.ReloadConfigCommand;
import com.bedless.spawnplugin.spawn.SetSpawnCommand;
import com.bedless.spawnplugin.spawn.SpawnCommand;
import com.bedless.spawnplugin.EventHandler;
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

public final class SpawnPlugin extends JavaPlugin{
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
            Bukkit.getConsoleSender().sendMessage(line1);
        } else {
            String line1 = ChatColor.GREEN + "===================";
            Bukkit.getConsoleSender().sendMessage(line1);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Custom Spawn Plugin");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
            Bukkit.getConsoleSender().sendMessage("Running on " + Bukkit.getBukkitVersion());
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "This Plugin is not running on the intended Spigot Version!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Plugin may not behave as expected!");
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
        getServer().getPluginManager().registerEvents(new EventHandler, this);
    }

    @Override
    public void onDisable() {
        ConfigurationManager.getInstance().saveConfiguration();
        String line2 = ChatColor.RED + "===================";
        Bukkit.getConsoleSender().sendMessage(line2);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Custom Spawn Plugin");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Disabled");
        Bukkit.getConsoleSender().sendMessage(line2);
    }
}
