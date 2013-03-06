package com.github.mineGeek.Areas.Util;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Players {

	public static Set<Player> getPlayerObjectsFromList( Set<String> names ) {
		
		Set<Player> players = new HashSet<Player>();
		for ( String x : names ) {
			if ( Bukkit.getPlayer(x).isOnline() ) players.add( Bukkit.getPlayer( x ));
		}		
		return players;
		
	}
}
