package com.bedless.spawnplugin.config;

import com.bedless.spawnplugin.SpawnPlugin;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {

    public static String baseFolderPath = SpawnPlugin.getInstance().getDataFolder().getPath();

    public static String mainConfigurationPath = SpawnPlugin.getInstance().getDataFolder().getPath() + File.separator + "main.yml";

    private static ConfigurationManager instance;

    private File baseFolder = new File(baseFolderPath);
    private File mainConfiguration = new File(mainConfigurationPath);

    private Map<String, Object> configurationOptions = new HashMap<>();

    private ConfigurationManager() {

        /* Spawn Module */

        configurationOptions.put(ConfigurationOption.SPAWN_X, 0d);
        configurationOptions.put(ConfigurationOption.SPAWN_Y, 0d);
        configurationOptions.put(ConfigurationOption.SPAWN_Z, 0d);
        configurationOptions.put(ConfigurationOption.SPAWN_YAW, 0f);
        configurationOptions.put(ConfigurationOption.SPAWN_PITCH, 0f);
        configurationOptions.put(ConfigurationOption.SPAWN_WORLD, "world");
        configurationOptions.put(ConfigurationOption.CLEAR_INV_JOIN, true);
        configurationOptions.put(ConfigurationOption.TELEPORT_TO_SPAWN_JOIN, true);
        configurationOptions.put(ConfigurationOption.PLAYER_IMMUNITY, true);

        configurationOptions.put(ConfigurationOption.PLAYER_IMMUNITY, true);
        configurationOptions.put(ConfigurationOption.SEND_TO_SPAWN_DEATH, true);
        configurationOptions.put(ConfigurationOption.CLEAR_INV_DEATH, true);
        configurationOptions.put(ConfigurationOption.PLAYER_HUNGER, false);
        configurationOptions.put(ConfigurationOption.PLAYER_INTERACT, false);
        configurationOptions.put(ConfigurationOption.PLACE_BLOCKS, false);
        configurationOptions.put(ConfigurationOption.BREAK_BLOCKS, false);
        if(!baseFolder.exists())
            baseFolder.mkdirs();

        if(!mainConfiguration.exists()) {
            try {
                /* Create new configuration file */
                mainConfiguration.createNewFile();

                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(mainConfiguration);
                yamlConfiguration.addDefaults(configurationOptions);
                yamlConfiguration.options().copyDefaults(true);
                yamlConfiguration.save(mainConfiguration);
                return;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        loadConfiguration();

    }

    public void loadConfiguration() {
        /* Config exists lets load values */
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(mainConfiguration);

        configurationOptions.replace(ConfigurationOption.SPAWN_X, yamlConfiguration.getDouble(ConfigurationOption.SPAWN_X));
        configurationOptions.replace(ConfigurationOption.SPAWN_Y, yamlConfiguration.getDouble(ConfigurationOption.SPAWN_Y));
        configurationOptions.replace(ConfigurationOption.SPAWN_Z, yamlConfiguration.getDouble(ConfigurationOption.SPAWN_Z));
        configurationOptions.replace(ConfigurationOption.SPAWN_YAW, (float) yamlConfiguration.getDouble(ConfigurationOption.SPAWN_YAW));
        configurationOptions.replace(ConfigurationOption.SPAWN_PITCH, (float)  yamlConfiguration.getDouble(ConfigurationOption.SPAWN_PITCH));
        configurationOptions.replace(ConfigurationOption.SPAWN_WORLD, yamlConfiguration.getString(ConfigurationOption.SPAWN_WORLD));
        configurationOptions.replace(ConfigurationOption.CLEAR_INV_JOIN, yamlConfiguration.getBoolean(ConfigurationOption.CLEAR_INV_JOIN));
        configurationOptions.replace(ConfigurationOption.PLAYER_IMMUNITY, yamlConfiguration.getBoolean(ConfigurationOption.PLAYER_IMMUNITY));
        configurationOptions.replace(ConfigurationOption.TELEPORT_TO_SPAWN_JOIN, yamlConfiguration.getBoolean(ConfigurationOption.TELEPORT_TO_SPAWN_JOIN));

        configurationOptions.replace(ConfigurationOption.PLAYER_IMMUNITY, yamlConfiguration.getBoolean(ConfigurationOption.PLAYER_IMMUNITY));
        configurationOptions.replace(ConfigurationOption.SEND_TO_SPAWN_DEATH, yamlConfiguration.getBoolean(ConfigurationOption.SEND_TO_SPAWN_DEATH));
        configurationOptions.replace(ConfigurationOption.CLEAR_INV_DEATH, yamlConfiguration.getBoolean(ConfigurationOption.CLEAR_INV_DEATH));
        configurationOptions.replace(ConfigurationOption.PLAYER_HUNGER, yamlConfiguration.getBoolean(ConfigurationOption.PLAYER_HUNGER));
        configurationOptions.replace(ConfigurationOption.PLAYER_INTERACT, yamlConfiguration.getBoolean(ConfigurationOption.PLAYER_INTERACT));
        configurationOptions.replace(ConfigurationOption.PLACE_BLOCKS, yamlConfiguration.getBoolean(ConfigurationOption.PLACE_BLOCKS));
        configurationOptions.replace(ConfigurationOption.BREAK_BLOCKS, yamlConfiguration.getBoolean(ConfigurationOption.BREAK_BLOCKS));

        //(boolean) getOption(ConfigurationOption.CLEAR_INV_JOIN)
    }

    public void saveConfiguration() {
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(mainConfiguration);
        configurationOptions.forEach(yamlConfiguration::set);
        try {
            yamlConfiguration.save(mainConfiguration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendToSpawn(Player p) {
        p.teleport(new Location(Bukkit.getWorld((String) getOption(ConfigurationOption.SPAWN_WORLD)),
                (double) getOption(ConfigurationOption.SPAWN_X),
                (double) getOption(ConfigurationOption.SPAWN_Y),
                (double) getOption(ConfigurationOption.SPAWN_Z),
                (float) getOption(ConfigurationOption.SPAWN_YAW),
                (float) getOption(ConfigurationOption.SPAWN_PITCH)));
    }


    public Object getOption(String opt) {
        return configurationOptions.get(opt);
    }

    /* Specific get functions are not type checked, if you call getInteger on a String it will result
        in an error and unpredictable behaviour. Always verify that the type of the ConfigurationOption
         used to retrieve an object matches the get functions.
    */

    public int getInteger(String opt) {
        return (int) getOption(opt);
    }

    public double getDouble(String opt) {
        return (double) getOption(opt);
    }

    public float getFloat(String opt) {
        return (float) getOption(opt);
    }

    public long getLong(String opt) {
        return (long) getOption(opt);
    }

    public boolean getBoolean(String opt) {
        return (boolean) getOption(opt);
    }

    public String getString(String opt) {
        return (String) getOption(opt);
    }

    public void setOption(String opt, Object obj) {
        configurationOptions.replace(opt, obj);
    }

    public static ConfigurationManager getInstance() {
        if(instance == null)
            instance = new ConfigurationManager();
        return instance;
    }

}
