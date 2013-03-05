package com.github.mineGeek.Areas.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Main.Registry;
import com.github.mineGeek.Areas.Structs.Area;


public class EventController {

	public static boolean PlayerEnterArea( Player p, Area area ) {
				
		OnAreaEnter event = new OnAreaEnter( p, area );
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		boolean result = !event.isCancelled();
		Registry.players.addPlayerToArea(p, area);
		
		return result;
		
	}
	
	public static boolean PlayerExitArea( Player p, Area area ) {
		
		OnAreaExit event = new OnAreaExit( p, area );
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		boolean result = !event.isCancelled();			
		Registry.players.removePlayerFromArea(p, area);
		
		return result;
	}
	
	public static void PlayerEnterChunk( Player p, Area area ) {
		p.sendMessage( "Chunk Enter: " + area.reset.tag );
	}

	public static void PlayerExitChunk( Player p, Area area ) {
		p.sendMessage( "Chunk Exit: " + area.reset.tag );
	}
}
