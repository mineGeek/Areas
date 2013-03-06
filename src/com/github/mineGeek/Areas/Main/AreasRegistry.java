package com.github.mineGeek.Areas.Main;

import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Structs.Area.PVPMode;
import com.github.mineGeek.Areas.Structs.RegistryPlayerLocations;
import com.github.mineGeek.Areas.Structs.RegistryReset;

public class AreasRegistry {

	public static RegistryPlayerLocations players = new RegistryPlayerLocations();
	public static RegistryReset resets = new RegistryReset();
	public static PVPMode defaultMode = PVPMode.OFF;
	
	public static void close() {
		players.close();
		resets.close();
	}
	
	public static void updatePlayerLocation( Player p ) {
		//Bukkit.getLogger().info("updatePlayerLocation");
		players.updatePlayerLocation(p);
		
	}
	
	public static void updatePlayerChunk( Player p, Integer oldHash ) {
		//Bukkit.getLogger().info("updatePlayerChunk");
		resets.playerMoveChunk(p, oldHash, p.getLocation().getChunk().hashCode() );
		
	}
	
	public static void updatePlayerMove( Player p ) {
		//Bukkit.getLogger().info("updatePlayerMove");
		resets.playerMove(p);
		
	}
}
