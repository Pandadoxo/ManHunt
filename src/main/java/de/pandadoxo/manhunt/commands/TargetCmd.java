// -----------------------
// Coded by Pandadoxo
// on 04.04.2021 at 14:43 
// -----------------------

package de.pandadoxo.manhunt.commands;

import de.pandadoxo.manhunt.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TargetCmd implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player p = (Player) sender;
        if (!p.hasPermission("manhunt.target")) {
            p.sendMessage(Main.PREFIX + "§7Du hast keine Berechtigung für diesen Befehl");
            return true;
        }

        if (args.length != 1) {
            p.sendMessage(Main.PREFIX + "§7Falscher Syntax! Benutze: §e/settarget <Player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(Main.PREFIX + "§7Dieser Spieler konnte nicht gefunden werden");
            return true;
        }

        Main.getTrackerCore().stop();
        Main.getTrackerCore().start(target);
        Bukkit.broadcastMessage(Main.PREFIX + "§b" + target.getName() + " §7ist nun der §dRunner§7. Jagt ihn sobald der Timer gestartet ist!");
        return false;
    }


}