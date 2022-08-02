package com.bedless.spawnplugin.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

public class MainConfiguration {

    private File file;
    private JSONObject json;
    private JSONParser parser = new JSONParser();
    private HashMap<String, Object> defaults = new HashMap<String, Object>();

    private JSONObject spawn_location;

    private JSONObject command_messages;

    private JSONObject plugin_configuration;

    private JSONObject spawn_configuration;

    private JSONObject spawn_configuration_join;

    private JSONObject spawn_configuration_death;

    private JSONObject spawn_configuration_general;


    public MainConfiguration(File file) {
        this.file = file;
        reload();
    }

    @SuppressWarnings("unchecked")
    public void reload() {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                PrintWriter pw = new PrintWriter(file, "UTF-8");
                pw.print("{");
                pw.print("}");
                pw.flush();
                pw.close();
            }
            json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            spawn_location = new JSONObject();
            spawn_location.put("1.x", 0.0);
            spawn_location.put("2.y", 0.0);
            spawn_location.put("3.z", 0.0);
            spawn_location.put("4.world", "world");
            spawn_location.put("5.pitch", 0.0);
            spawn_location.put("6.yaw", 0.0);
            defaults.put("Spawn_Location", spawn_location);

            command_messages = new JSONObject();
            command_messages.put("1.Spawn_Command", "§aYou have spawned!");
            command_messages.put("2.Build_Command_On", "§aYou have Enabled Build Mode!");
            command_messages.put("3.Build_Command_Off", "§cYou have Disabled Build Mode!");
            command_messages.put("4.Set_Spawn_Command", "§aYou have set the Spawn Location!");
            command_messages.put("5.Reload_Command", "§6You have reloaded the plugin!");
            defaults.put("Command_Messages", command_messages);

            plugin_configuration = new JSONObject();
            plugin_configuration.put("1.Prefix", "§8§l[§3Spawn Plugin§8§l] ");
            defaults.put("Plugin_Configuration", plugin_configuration);

            spawn_configuration = new JSONObject();
            spawn_configuration.put("Join", spawn_configuration_join);
            spawn_configuration.put("Death", spawn_configuration_death);
            spawn_configuration.put("General", spawn_configuration_general);
            defaults.put("Spawn_Configuration", spawn_configuration);

            spawn_configuration_join = new JSONObject();
            spawn_configuration_join.put("1.Teleport_To_Spawn", true);
            spawn_configuration_join.put("2.Clear_Inventory", true);
            spawn_configuration_join.put("3.Player_Invincible", true);

            spawn_configuration_death = new JSONObject();
            spawn_configuration_death.put("1.Teleport_To_Spawn", true);
            spawn_configuration_death.put("2.Clear_Inventory", true);

            spawn_configuration_general = new JSONObject();
            spawn_configuration_general.put("1.Block_Break", false);
            spawn_configuration_general.put("2.Block_Place", false);
            spawn_configuration_general.put("3.Interactions", false);
            spawn_configuration_general.put("4.Hunger", false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public boolean save() {
        try {
            JSONObject toSave = new JSONObject();

            for (String s : defaults.keySet()) {
                Object o = defaults.get(s);
                if (o instanceof String) {
                    toSave.put(s, getString(s));
                } else if (o instanceof Double) {
                    toSave.put(s, getDouble(s));
                } else if (o instanceof Integer) {
                    toSave.put(s, getInteger(s));
                } else if (o instanceof JSONObject) {
                    toSave.put(s, getObject(s));
                } else if (o instanceof JSONArray) {
                    toSave.put(s, getArray(s));
                }
            }

            TreeMap<String, Object> treeMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
            treeMap.putAll(toSave);

            Gson g = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonString = g.toJson(treeMap);

            FileWriter fw = new FileWriter(file);
            fw.write(prettyJsonString);
            fw.flush();
            fw.close();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public JSONObject getSpawnLocationRaw() {
        return spawn_location;
    }

    public JSONObject getCommandMessages() {
        return command_messages;
    }

    public JSONObject getPluginConfiguration() {
        return plugin_configuration;
    }

    public Location getSpawnLocation() {
        return new Location(
                Bukkit.getWorld(spawn_location.get("4.world").toString()),
                (int) spawn_location.get("1.x"),
                (int) spawn_location.get("2.y"),
                (int) spawn_location.get("3.z"),
                (float) spawn_location.get("5.pitch"),
                (float) spawn_location.get("6.yaw")
        );
    }

    public void setSpawnLocation(Location loc) {
        spawn_location.replace("1.x", loc.getX());
        spawn_location.replace("2.y", loc.getY());
        spawn_location.replace("3.z", loc.getZ());
        spawn_location.replace("4.world", loc.getWorld().getName());
        spawn_location.replace("5.pitch", loc.getPitch());
        spawn_location.replace("6.yaw", loc.getYaw());
    }

    public String getRawData(String key) {
        if (!json.containsKey(key)) return "";

        return String.valueOf(defaults.get(key));
    }

    public File getFile() {
        return file;
    }


    public String getString(String key) {
        return ChatColor.translateAlternateColorCodes('§', getRawData(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.valueOf(getRawData(key));
    }

    public double getDouble(String key) {
        try {
            return Double.parseDouble(getRawData(key));
        } catch (Exception ex) {
        }
        return -1;
    }

    public double getInteger(String key) {
        try {
            return Integer.parseInt(getRawData(key));
        } catch (Exception ex) {
        }
        return -1;
    }

    public JSONObject getObject(String key) {
        return json.containsKey(key) ? (JSONObject) json.get(key)
                : (defaults.containsKey(key) ? (JSONObject) defaults.get(key) : new JSONObject());
    }

    public JSONArray getArray(String key) {
        return json.containsKey(key) ? (JSONArray) json.get(key)
                : (defaults.containsKey(key) ? (JSONArray) defaults.get(key) : new JSONArray());
    }


    public boolean contains(String key) {
        return json.containsKey(key);
    }

    public void remove(String key) {
        json.remove(key);
    }
}