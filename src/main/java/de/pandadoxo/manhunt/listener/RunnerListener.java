// -----------------------
// Coded by Pandadoxo
// on 04.04.2021 at 13:58 
// -----------------------

package de.pandadoxo.manhunt.listener;

import de.pandadoxo.manhunt.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class RunnerListener implements Listener {

    @EventHandler
    public void onPortalEnter(PlayerPortalEvent event) {
        Player p = event.getPlayer();
        if (event.getTo() == null) {
            return;
        }
        if (Main.getTrackerCore().runner == null) {
            return;
        }
        if (Main.getTrackerCore().runner == p) {
            Main.getTrackerCore().runnerPortal = event.getFrom();
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        if (Main.getTrackerCore().runner == null) {
            return;
        }
        if (Main.getTrackerCore().runner == p) {
            Bukkit.broadcastMessage(Main.PREFIX + "§7Der §dRunner §7ist gestorben. Die §aHunter §7haben gewonnen!");
            Main.getTrackerCore().stop();
        }
    }

    @EventHandler
    public void onDragonKill(EntityDeathEvent event) {
        if (Main.getTrackerCore().runner == null) {
            return;
        }
        if(event.getEntity() instanceof EnderDragon) {
            Bukkit.broadcastMessage(Main.PREFIX + "§7Der §bEnder Drache §7wurde getötet. Der §dRunner §7hat gewonnen!");
            Main.getTrackerCore().stop();
        }
    }

}
