package com.bedless.spawnplugin.events;

import org.bukkit.event.Listener;

public class MainEventHandler implements Listener {
    /*
@MainEventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (ConfigurationManager.getInstance().getBoolean(TELEPORT_TO_SPAWN_JOIN)) {
            ConfigurationManager.getInstance().sendToSpawn(player);
            player.setHealth(20);
            player.setFoodLevel(20);
        }
        if (ConfigurationManager.getInstance().getBoolean(CLEAR_INV_JOIN)) {
            player.getInventory().clear();
        }
    }

    @MainEventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player player = e.getEntity();
        if (ConfigurationManager.getInstance().getBoolean(SEND_TO_SPAWN_DEATH)) {
            ConfigurationManager.getInstance().sendToSpawn(player);
            player.setHealth(20);
            player.setFoodLevel(20);
        }
        if (ConfigurationManager.getInstance().getBoolean(CLEAR_INV_DEATH)) {
            player.getInventory().clear();
        }
    }

    @MainEventHandler
    public void onQuit(PlayerQuitEvent e) {
        //Removes player off of Build List on Logout 
        playersInBuildMode.remove(e.getPlayer());
    }

    @MainEventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        //e.setCancelled(true);
    }

    @MainEventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (ConfigurationManager.getInstance().getBoolean(PLAYER_IMMUNITY)) {
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

    @MainEventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_INTERACT)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
        if (playersInBuildMode.contains(e.getPlayer())) {
            e.setCancelled(false);
        }
    }

    @MainEventHandler
    public void onFireTickEvent(BlockBurnEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_IMMUNITY)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @MainEventHandler
    public void onTntExplodeEvent(ExplosionPrimeEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_IMMUNITY)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @MainEventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_HUNGER)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @MainEventHandler
    public void onPlayerBreakEvent(BlockBreakEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(BREAK_BLOCKS)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
        if (playersInBuildMode.contains(e.getPlayer())) {
            e.setCancelled(false);
        }
    }
    @MainEventHandler
    public void onPlayerPlaceEvent(BlockPlaceEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLACE_BLOCKS)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
        if (playersInBuildMode.contains(e.getPlayer())) {
            e.setCancelled(false);
        }
    }

     */

}