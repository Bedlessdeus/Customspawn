package com.bedless.spawnplugin.spawn;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.bedless.spawnplugin.config.ConfigurationManager;
import com.bedless.spawnplugin.config.ConfigurationOption;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandAlias("setspawn")
@CommandPermission("Customspawn.setspawn")
public class SetSpawnCommand extends BaseCommand {

    @Default
    public void onCommand(Player p, String[] args) {
        Location loc = p.getLocation();
        ConfigurationManager cm = ConfigurationManager.getInstance();
        cm.setOption(ConfigurationOption.SPAWN_WORLD, loc.getWorld().getName());
        cm.setOption(ConfigurationOption.SPAWN_X, loc.getX());
        cm.setOption(ConfigurationOption.SPAWN_Y, loc.getY());
        cm.setOption(ConfigurationOption.SPAWN_Z, loc.getZ());
        cm.setOption(ConfigurationOption.SPAWN_YAW, loc.getYaw());
        cm.setOption(ConfigurationOption.SPAWN_PITCH, loc.getPitch());
        p.sendMessage("You have set Spawn at your location!");
    }
}
