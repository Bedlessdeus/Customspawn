package com.bedless.spawnplugin.spawn;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.bedless.spawnplugin.SpawnPlugin;
import com.bedless.spawnplugin.utils.Singleton;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;


@CommandAlias("betterspawn|bs")
public class MainCommand extends BaseCommand {

    @Default
    public void defaultOut(Player player) {
        if (player.hasPermission("")) {
            Singleton.getInstance().printHelp(player);
        } else {
            player.sendMessage(Singleton.getInstance().versionLine1);
        }
    }

    @Subcommand("ver")
    public void versionCommandPlayer(Player player) {
        player.sendMessage(Singleton.getInstance().versionLine1);
    }

    /**
     * @Fix Will Be fixed, Current AIKAR Has Issue With this
     */
    /*
    @Subcommand("ver")
    public void versionCommandConsole(ConsoleCommandSender sender){
        sender.sendMessage(Singleton.getInstance().versionLine1);
    }
     */
    @Subcommand("build")
    @CommandPermission("BetterSpawn.command.build")
    public void buildCommand(Player player) {
        if (Singleton.getInstance().playersInBuildMode.contains(player)) {
            Singleton.getInstance().playersInBuildMode.remove(player);
            player.sendMessage(SpawnPlugin.getInstance().mainConfiguration.getPluginConfiguration().get("1.Prefix") + ChatColor.translateAlternateColorCodes('&',
                    (String) SpawnPlugin.getInstance().mainConfiguration.getCommandMessages().get("3.Build_Command_Off")));
        } else {
            Singleton.getInstance().playersInBuildMode.add(player);
            player.sendMessage(SpawnPlugin.getInstance().mainConfiguration.getPluginConfiguration().get("1.Prefix") + ChatColor.translateAlternateColorCodes('&',
                    (String) SpawnPlugin.getInstance().mainConfiguration.getCommandMessages().get("2.Build_Command_On")));
        }
    }

    @Subcommand("reload")
    @CommandPermission("BetterSpawn.command.reload")
    public void reloadPlugin(Player player) {
        player.sendMessage(SpawnPlugin.getInstance().mainConfiguration.getString("1.Prefix") + ChatColor.translateAlternateColorCodes('§',
                (String) SpawnPlugin.getInstance().mainConfiguration.getCommandMessages().get("1.Reload_Command")));
        Singleton.getInstance().main.getPluginLoader().disablePlugin(Singleton.getInstance().main);
        Singleton.getInstance().main.getPluginLoader().enablePlugin(Singleton.getInstance().main);
    }

    @Subcommand("setspawn")
    @CommandPermission("BetterSpawn.command.setspawn")
    public void setSpawn(Player player) {
        player.sendMessage(SpawnPlugin.getInstance().mainConfiguration.getPluginConfiguration().get("1.Prefix") + ChatColor.translateAlternateColorCodes('&',
                (String) SpawnPlugin.getInstance().mainConfiguration.getCommandMessages().get("4.Set_Spawn_Command")));
        Location loc = player.getLocation();
        SpawnPlugin.getInstance().mainConfiguration.setSpawnLocation(loc);
    }


}
