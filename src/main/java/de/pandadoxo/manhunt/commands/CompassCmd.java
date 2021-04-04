// -----------------------
// Coded by Pandadoxo
// on 04.04.2021 at 17:02 
// -----------------------

package de.pandadoxo.manhunt.commands;

import de.pandadoxo.manhunt.Main;
import de.pandadoxo.manhunt.core.Tracker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CompassCmd implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player p = (Player) sender;
        if (Main.getTrackerCore().runner == null) {
            p.sendMessage(Main.PREFIX + "§7Es ist noch kein §dRunner §7gesetzt");
            return true;
        }

        if (Main.getTrackerCore().runner == p) {
            p.sendMessage(Main.PREFIX + "§7Du bist der §dRunner§7. Renn lieber :)");
            return true;
        }

        Tracker tracker = Main.getTrackerCore().getTracker(p);
        if(tracker == null) {
            p.sendMessage(Main.PREFIX + "§7Huch, du bist garnicht registriert. Kann es sein, dass du bei Start nicht dabei warst?");
            return true;
        }

        if (Main.getTrackerCore().getCompass(tracker) == null) {
            Main.getTrackerCore().giveTracker(tracker);
            p.sendMessage(Main.PREFIX + "§7Hier, bitteschön :3");
            return true;
        }

        p.sendMessage(Main.PREFIX + "§7Mhm, sieht so aus, als hättest du bereits einen Kompass im Inventar. Such einfach weiter :)");
        return false;
    }


}