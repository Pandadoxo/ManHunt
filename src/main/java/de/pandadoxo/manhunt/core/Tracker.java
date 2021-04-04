// -----------------------
// Coded by Pandadoxo
// on 04.04.2021 at 13:28 
// -----------------------

package de.pandadoxo.manhunt.core;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Tracker {

    private Player player;
    private Location target;

    public Tracker(Player player, Location target) {
        this.player = player;
        this.target = target;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getTarget() {
        return target;
    }

    public void setTarget(Location target) {
        this.target = target;
    }
}
