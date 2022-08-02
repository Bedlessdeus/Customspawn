package com.bedless.spawnplugin.spawn;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.bedless.spawnplugin.SpawnPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("spawn")
@CommandPermission("BetterSpawn.command.spawn")
public class SpawnCommand extends BaseCommand {

    @Default
    public void onCommand(Player player) {
        player.teleport(SpawnPlugin.getInstance().mainConfiguration.getSpawnLocation());
        player.sendMessage(SpawnPlugin.getInstance().mainConfiguration.getPluginConfiguration().get("1.Prefix") + ChatColor.translateAlternateColorCodes('&',
                (String) SpawnPlugin.getInstance().mainConfiguration.getCommandMessages().get("1.Spawn_Command")));
    }
}
