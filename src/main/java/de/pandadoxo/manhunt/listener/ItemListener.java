// -----------------------
// Coded by Pandadoxo
// on 04.04.2021 at 13:58 
// -----------------------

package de.pandadoxo.manhunt.listener;

import de.pandadoxo.manhunt.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;

public class ItemListener implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (Main.getTrackerCore().runner == null) {
            return;
        }
        if (Main.getTrackerCore().runner == event.getPlayer()) {
            return;
        }
        if (event.getItemDrop().getItemStack().isSimilar(Main.getInstance().trackerItem)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrag(InventoryMoveItemEvent event) {
        if (!(event.getInitiator().getHolder() instanceof Player)) {
            return;
        }
        Player p = (Player) event.getInitiator().getHolder();
        if (Main.getTrackerCore().runner == null) {
            return;
        }
        if (Main.getTrackerCore().runner == p) {
            return;
        }
        if (event.getDestination() == event.getInitiator()) {
            return;
        }
        if (event.getItem().isSimilar(Main.getInstance().trackerItem)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        if (Main.getTrackerCore().runner == null) {
            return;
        }
        if (Main.getTrackerCore().runner == p) {
            return;
        }

        Iterator<ItemStack> drops = event.getDrops().iterator();
        while (drops.hasNext()) {
            ItemStack drop = drops.next();
            if (drop != null && drop.isSimilar(Main.getInstance().trackerItem)) {
                event.getDrops().remove(drop);
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if (Main.getTrackerCore().runner == null) {
            return;
        }
        if (Main.getTrackerCore().runner == p) {
            return;
        }
        Main.getTrackerCore().giveTracker();
    }

}
