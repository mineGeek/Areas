package com.github.mineGeek.Areas.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.mineGeek.Areas.Structs.Area;

public class OnAreaExit extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private Player player = null;
	private Area area = null;
	
	public OnAreaExit( Player player, Area area ) {
		this.player = player;
		this.area = area;
		this.cancelled = false;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Area getArea() {
		return this.area;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
		this.setCancelled( cancel );
		
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
