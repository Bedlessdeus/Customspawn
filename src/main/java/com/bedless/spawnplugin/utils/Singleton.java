package com.bedless.spawnplugin.utils;

import com.bedless.spawnplugin.SpawnPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.logging.Level;

public class Singleton {

    private static Singleton instance;
    /**
     * @Setter Register the plugin instance for use
     * @Getter get The Instance of the Main Class
     */

    public SpawnPlugin main = SpawnPlugin.getInstance();
    public final String pluginVersion = ChatColor.DARK_GRAY + "2.0";
    public final String command1 = ChatColor.GOLD + "/spawn" + ChatColor.GRAY + " - " + ChatColor.GREEN + "Teleports you to Spawn";
    public final String command2 = ChatColor.GOLD + "/bs setspawn" + ChatColor.GRAY + " - " + ChatColor.GREEN + "Sets Spawn Location";
    public final String command3 = ChatColor.GOLD + "/bs reload" + ChatColor.GRAY + " - " + ChatColor.GREEN + "Reloads The Plugin";
    public final String command4 = ChatColor.GOLD + "/bs help" + ChatColor.GRAY + " - " + ChatColor.GREEN + "Displays this Message";
    public final String command5 = ChatColor.GOLD + "/bs build" + ChatColor.GRAY + " - " + ChatColor.GREEN + "Toggles Build Mode";
    /**
     * @Getter Build Command Handling
     */

    public ArrayList<Player> playersInBuildMode = new ArrayList<>();
    /**
     * @Getter All Strings Required to Startup!
     */

    private final String headerFooterS = ChatColor.GREEN + "===================";
    private final String headerFooterF = ChatColor.RED + "===================";
    private final String pluginName = ChatColor.DARK_AQUA + " Better Spawn ";
    /**
     * @Getter Help Command Handling
     */

    public String helpHeaderFooter = ChatColor.GOLD + "=======" + pluginName + ChatColor.GOLD + "=======";
    /**
     * @Getter Version Information
     */

    public final String versionLine1 = ChatColor.DARK_AQUA + "This Server Is Running " + pluginName + ChatColor.DARK_AQUA + " On Version " + pluginVersion;
    private final String serverVersion = Bukkit.getVersion();
    private final String Success = ChatColor.GREEN + "Enabled";
    private final String Failed = ChatColor.RED + "Failed";

    private Singleton() {
    }

    /**
     * @Getter Gain Access to the Main Singleton
     * @Setter If needed, set the Instance of the Main Singleton
     */

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void printStartupMessage(ConsoleCommandSender sendto) {
        if (serverVersion.contains("1.19")) {
            sendto.sendMessage(headerFooterS);
            sendto.sendMessage(pluginName);
            sendto.sendMessage(pluginVersion);
            sendto.sendMessage(serverVersion);
            sendto.sendMessage(Success);
            sendto.sendMessage(headerFooterS);
        } else {
            sendto.sendMessage(headerFooterF);
            sendto.sendMessage(pluginName);
            sendto.sendMessage(pluginVersion);
            sendto.sendMessage(serverVersion);
            sendto.sendMessage(Failed);
            sendto.sendMessage(headerFooterF);

            Bukkit.getLogger().log(Level.SEVERE, "This Plugin Is Not Compatible With Your Server Version!");
            Bukkit.getLogger().log(Level.SEVERE, "This Plugin will be shutting itself down!");
            main.getPluginLoader().disablePlugin(main);
        }
    }

    public void printHelp(Player player) {
        player.sendMessage(helpHeaderFooter);
        player.sendMessage(command1);
        player.sendMessage(command2);
        player.sendMessage(command3);
        player.sendMessage(command4);
        player.sendMessage(command5);
        player.sendMessage(helpHeaderFooter);
    }
}
