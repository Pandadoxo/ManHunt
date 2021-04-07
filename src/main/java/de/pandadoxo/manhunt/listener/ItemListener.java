// -----------------------
// Coded by Pandadoxo
// on 04.04.2021 at 13:58 
// -----------------------

package de.pandadoxo.manhunt.listener;

import de.pandadoxo.manhunt.Main;
import de.pandadoxo.manhunt.event.TimerCallEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

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
        if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(Main.getInstance().trackerItem.getItemMeta().getDisplayName())) {
            event.setCancelled(true);
            event.getItemDrop().remove();
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (Main.getTrackerCore().runner == null) {
            return;
        }
        if (!event.getWhoClicked().getOpenInventory().getType().isCreatable()) {
            return;
        }
        if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() &&
                event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.getInstance().trackerItem.getItemMeta().getDisplayName())) {
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
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
            if (drop != null && drop.getItemMeta().getDisplayName().equals(Main.getInstance().trackerItem.getItemMeta().getDisplayName())) {
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
