package com.github.mineGeek.Areas.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Structs.Area;


public class EventController {

	public static boolean PlayerEnterArea( Player p, Area area ) {
		
		p.sendMessage( "Entering: " + area.reset.tag );
		
		OnAreaEnter event = new OnAreaEnter( p, area );
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		return !event.isCancelled();		
		
	}
	
	public static boolean PlayerExitArea( Player p, Area area ) {
		
		p.sendMessage( "Exiting: " + area.reset.tag );
		OnAreaExit event = new OnAreaExit( p, area );
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		return !event.isCancelled();			
		
	}
	
	public static void PlayerEnterChunk( Player p, Area area ) {
		p.sendMessage( "Chunk Enter: " + area.reset.tag );
	}

	public static void PlayerExitChunk( Player p, Area area ) {
		p.sendMessage( "Chunk Exit: " + area.reset.tag );
	}
}
