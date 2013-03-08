package com.github.mineGeek.Areas.Main;

import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Structs.Area.PVPMode;
import com.github.mineGeek.Areas.Structs.IAreaEventsHandler;
import com.github.mineGeek.Areas.Structs.RegistryPlayerLocations;

public class AreasRegistry {

	public static RegistryPlayerLocations 	players 	= new RegistryPlayerLocations();
	public static IAreaEventsHandler 		areaHandler = null;
	
	public static PVPMode defaultMode = PVPMode.OFF;
	
	public static void close() {
		players.close();
	}
	
	public static void updatePlayerLocation( Player p ) {
		players.updatePlayerLocation(p);
		
	}
	
	public static void updatePlayerChunk( Player p, Integer oldHash ) {
		areaHandler.playerMoveChunk(p, oldHash, p.getLocation().getChunk().hashCode() );
		
	}
	
	public static void updatePlayerMove( Player p ) {
		areaHandler.playerMove(p);
		
	}
}
