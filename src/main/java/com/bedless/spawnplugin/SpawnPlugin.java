package com.bedless.spawnplugin;

import co.aikar.commands.PaperCommandManager;
import com.bedless.spawnplugin.configuration.MainConfiguration;
import com.bedless.spawnplugin.spawn.MainCommand;
import com.bedless.spawnplugin.spawn.SpawnCommand;
import com.bedless.spawnplugin.utils.Singleton;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public final class SpawnPlugin extends JavaPlugin {
    private static SpawnPlugin INSTANCE;

    public MainConfiguration mainConfiguration;

    public static SpawnPlugin getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        mainConfiguration = new MainConfiguration(new File(getDataFolder(), "SpawnLocation.json"));
        mainConfiguration.reload();
        mainConfiguration.save();
        Singleton.getInstance().printStartupMessage(Bukkit.getConsoleSender());
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        mainConfiguration.save();
    }

    public void registerCommands() {
        PaperCommandManager pcm = new PaperCommandManager(this);
        pcm.registerCommand(new MainCommand());
        pcm.registerCommand(new SpawnCommand());
    }

    public void registerEvents() {

    }

}
