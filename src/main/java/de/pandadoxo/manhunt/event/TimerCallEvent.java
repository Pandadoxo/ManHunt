// -----------------------
// Coded by Pandadoxo
// on 04.04.2021 at 19:59 
// -----------------------

package de.pandadoxo.manhunt.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimerCallEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private String message;

    public TimerCallEvent(String message) {
        this.message = message;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public String getMessage() {
        return message;
    }
}
