package com.github.mineGeek.Areas.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Main.AreasRegistry;
import com.github.mineGeek.Areas.Structs.Area;


public class AreasEventController {

	public static boolean PlayerEnterArea( Player p, Area area ) {
				
		OnAreaEnter event = new OnAreaEnter( p, area );
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		boolean result = !event.isCancelled();
		AreasRegistry.players.addPlayerToArea(p, area);
		
		return result;
		
	}
	
	public static boolean PlayerExitArea( Player p, Area area ) {
		
		OnAreaExit event = new OnAreaExit( p, area );
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		boolean result = !event.isCancelled();			
		AreasRegistry.players.removePlayerFromArea(p, area);
		
		return result;
	}

}
