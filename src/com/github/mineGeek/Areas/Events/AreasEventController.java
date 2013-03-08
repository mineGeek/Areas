package com.github.mineGeek.Areas.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Main.AreasRegistry;
import com.github.mineGeek.Areas.Structs.Area;


public class AreasEventController {

	/**
	 * Called when a player moves into an area. Generates
	 * a Bukkit event for other plugins to respond to.
	 * @param player
	 * @param area
	 * @return true is not cancelled.
	 */
	public static boolean PlayerEnterArea( Player p, Area area ) {
				
		OnAreaEnter event = new OnAreaEnter( p, area );
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		boolean result = !event.isCancelled();
		AreasRegistry.players.addPlayerToArea(p, area);
		
		return result;
		
	}
	
	/**
	 * Called when a player moves out of an area. Generates
	 * a Bukkit even for other plugins to respond to
	 * @param player
	 * @param area
	 * @return true if not cancelled
	 */
	public static boolean PlayerExitArea( Player p, Area area ) {
		
		OnAreaExit event = new OnAreaExit( p, area );
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		boolean result = !event.isCancelled();			
		AreasRegistry.players.removePlayerFromArea(p, area);
		
		return result;
	}

}
