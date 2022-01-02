package com.bedless.spawnplugin.spawn;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.bedless.spawnplugin.config.ConfigurationManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("spawn")
@CommandPermission("Customspawn.spawn")
public class SpawnCommand extends BaseCommand {

    @Default
    public void onCommand(Player p) {
        ConfigurationManager.getInstance().sendToSpawn(p);
        p.sendMessage(ChatColor.GREEN + "You were Teleported to spawn!");
    }
}
