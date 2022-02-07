package com.bedless.spawnplugin.eventhandler;

public class EventHandler implements EventHandler{
@EventHandler
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

    @EventHandler
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

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        //Removes player off of Build List on Logout 
        playersInBuildMode.remove(e.getPlayer());
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        //e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (ConfigurationManager.getInstance().getBoolean(PLAYER_IMMUNITY)) {
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
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

    @EventHandler
    public void onFireTickEvent(BlockBurnEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_IMMUNITY)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onTntExplodeEvent(ExplosionPrimeEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_IMMUNITY)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (ConfigurationManager.getInstance().getBoolean(PLAYER_HUNGER)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
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
    @EventHandler
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

}