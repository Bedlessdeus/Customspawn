package com.bedless.spawnplugin.spawn;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@CommandAlias("build")
@CommandPermission("Customspawn.build")
public class BuildCommand extends BaseCommand {

    public static ArrayList<Player> playersInBuildMode = new ArrayList<>();

    @Default
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("CalydonSpawn.buildmode")) {
            if (playersInBuildMode.contains(p)) {
                playersInBuildMode.remove(p);
                p.sendMessage(ChatColor.DARK_RED + "You have De-Activated Build Mode");
            } else {
                playersInBuildMode.add(p);
                p.sendMessage(ChatColor.DARK_GREEN + "You have Activated Build Mode");
            }
        }
    }
}
