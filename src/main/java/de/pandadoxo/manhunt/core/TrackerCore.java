// -----------------------
// Coded by Pandadoxo
// on 04.04.2021 at 13:31 
// -----------------------

package de.pandadoxo.manhunt.core;

import de.pandadoxo.manhunt.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class TrackerCore {

    public List<Tracker> trackers = new ArrayList<>();
    public Player runner;
    public Location runnerPortal;

    private BukkitTask task;

    public boolean start(Player runner) {
        if (runner == null || !runner.isOnline()) {
            return false;
        }
        if (task != null) {
            return false;
        }
        this.runner = runner;
        this.runner.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100000, 9, true));
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all == runner) {
                continue;
            }
            addTracker(all);
        }

        giveTracker();
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
        return true;
    }

    public boolean stop() {
        if (task == null) {
            return false;
        }
        task.cancel();
        task = null;
        runner.removePotionEffect(PotionEffectType.GLOWING);
        runner = null;
        trackers.clear();
        return true;
    }

    public void update() {
        if (this.task == null) {
            return;
        }
        if (this.runner == null || !this.runner.isOnline()) {
            stop();
            return;
        }

        // Set Compass Target
        for (Tracker tracker : trackers) {
            if (tracker.getPlayer().getWorld() == runner.getWorld()) {
                tracker.setTarget(runner.getLocation());
            } else if (runnerPortal != null) {
                tracker.setTarget(runnerPortal);
            }

            setTarget(tracker);
        }
    }

    public void setTarget(Tracker tracker) {
        ItemStack compass = getCompass(tracker);
        if (compass == null) return;

        CompassMeta meta = (CompassMeta) compass.getItemMeta();
        meta.setLodestone(tracker.getTarget());
        meta.setLodestoneTracked(false);
        compass.setItemMeta(meta);
    }

    public ItemStack getCompass(Tracker tracker) {
        for (ItemStack item : tracker.getPlayer().getInventory().getContents()) {
            if (item.getItemMeta().getDisplayName().equals(Main.getInstance().trackerItem.getItemMeta().getDisplayName())) {
                return item;
            }
        }
        giveTracker(tracker);
        return null;
    }

    public Tracker getTracker(Player player) {
        for (Tracker tracker : trackers) {
            if (tracker.getPlayer() == player) {
                return tracker;
            }
        }
        return null;
    }

    public void giveTracker() {
        for (Tracker tracker : trackers) {
            giveTracker(tracker);
        }
    }

    public void giveTracker(Tracker tracker) {
        tracker.getPlayer().getInventory().addItem(Main.getInstance().trackerItem);
    }

    public void addTracker(Player player) {
        trackers.add(new Tracker(player, runner != null ? runner.getLocation() : player.getLocation()));
    }

    public void removeTracker(Player player) {
        if (getTracker(player) != null) {
            trackers.remove(getTracker(player));
        }
    }

}
