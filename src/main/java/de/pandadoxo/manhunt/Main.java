package de.pandadoxo.manhunt;

import de.pandadoxo.manhunt.commands.CompassCmd;
import de.pandadoxo.manhunt.commands.TargetCmd;
import de.pandadoxo.manhunt.core.TrackerCore;
import de.pandadoxo.manhunt.listener.ItemListener;
import de.pandadoxo.manhunt.listener.RunnerListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static final String PREFIX = "§8[§l§2Melonen§fHunt§8] §r";

    private static Main instance;
    private static TrackerCore trackerCore;

    public ItemStack trackerItem;

    @Override
    public void onEnable() {
        instance = this;
        trackerCore = new TrackerCore();

        // Tracker
        trackerItem = new ItemStack(Material.COMPASS);
        ItemMeta trackerMeta = trackerItem.getItemMeta();
        trackerMeta.setDisplayName("§fTracker");
        trackerItem.setItemMeta(trackerMeta);

        getCommand("settarget").setExecutor(new TargetCmd());
        getCommand("compass").setExecutor(new CompassCmd());
        Bukkit.getPluginManager().registerEvents(new ItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new RunnerListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

    public static TrackerCore getTrackerCore() {
        return trackerCore;
    }
}
