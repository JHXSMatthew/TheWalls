package com.github.JHXSMatthew.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Matthew on 2016/5/21.
 */
public final class GameInitReadyEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public GameInitReadyEvent() {
        System.out.println("GameEvent -> GameInitReadyEvent fired!!!!!");
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}