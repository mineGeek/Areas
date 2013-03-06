package com.github.mineGeek.Areas.Structs;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Main.AreasRegistry;
import com.github.mineGeek.Areas.Structs.Area.PVPMode;

public class RegistryPlayerLocations {

	public Map<String, Integer> playerToChunk = new WeakHashMap<String, Integer>();
	public Map<Integer, Set<String>> chunkToPlayers = new WeakHashMap<Integer, Set<String>>();
	public Map<String, Set<Area>> playersToArea = new WeakHashMap<String, Set<Area>>();
	
	private void clear() {
		playerToChunk.clear();
		chunkToPlayers.clear();
	}
	
	public void loadAllOnlinePlayers() {
		
		clear();
		
		for ( Player p : Bukkit.getOnlinePlayers() ) {
			updatePlayerLocation( p );
		}
		
		
	}
	
	public PVPMode getPlayerPVPMode( Player p ) {
		
		PVPMode mode = AreasRegistry.defaultMode;
		
		if ( playersToArea.containsKey( p.getName() ) ) {
			
			for( Area a : playersToArea.get( p.getName() ) ) {
				mode = a.pvpMode;
			}
			
		}
		
		return mode;
		
	}
	
	public void addPlayerToArea( Player p, Area a ) {
		
		if ( !playersToArea.containsKey(p.getName() ) ) {
			playersToArea.put( p.getName(), new HashSet<Area>() );
		}
		
		playersToArea.get( p.getName() ).add( a );
		
	}
	
	public void removePlayerFromArea( Player p, Area a ) {
		
		if ( !playersToArea.containsKey(p.getName() ) ) {
			return;
		}
		
		playersToArea.get( p.getName() ).remove( a );		
		
	}
	
	private void addPlayer( String playerName, Integer chunkHash ) {
		
		removePlayer( playerName );
		playerToChunk.put(playerName, chunkHash );
		
		if ( !chunkToPlayers.containsKey( chunkHash ) ) {
			chunkToPlayers.put( chunkHash, new HashSet<String>() );
		}
		
		chunkToPlayers.get(chunkHash).add( playerName );
		
	}
	
	private void removePlayer( String playerName ) {
		
		Integer x = playerToChunk.containsKey( playerName ) ? playerToChunk.get( playerName ) : null;
		
		if ( x != null ) {
			if ( chunkToPlayers.containsKey(x) ) chunkToPlayers.get(x).remove( playerName );
		}
		
	}
	
	public Set<Integer> getChunksWithPlayers() {
		
		Set<Integer> list = new HashSet<Integer>();
		
		for ( Integer x : chunkToPlayers.keySet() ) {
			list.add( x );
		}
		
		return list;
		
	}
	
	public Set<String> getPlayersInChunks( Set<Integer> hashes ) {
		
		Set<String> list = new HashSet<String>();
		
		for ( Integer x : hashes ) {
			list.addAll( chunkToPlayers.get(x) );
		}
		
		return list;
		
	}
	
	public Set<Player> getPlayerObjectsInChunks( HashSet<Integer> hashes ) {
		
		Set<String> l = getPlayersInChunks( hashes );
		Set<Player> list = new HashSet<Player>();
		
		for ( String x : l ) {
			list.add( Bukkit.getPlayer(x) );
		}
		
		return list;
	}
	
	public Integer getChunkOfPlayer( String playerName ) {
		return playerToChunk.containsKey(playerName) ? playerToChunk.get(playerName) : null;
	}
	
	
	public void updatePlayerLocation( Player p ) {
		
		Integer hash = p.getLocation().getChunk().hashCode();
		if ( getChunkOfPlayer( p.getName() ) == null ) {
			addPlayer( p.getName(), hash );
			AreasRegistry.updatePlayerChunk(p, hash );
		} else if ( !getChunkOfPlayer( p.getName() ).equals( hash ) ) {
			removePlayer( p.getName() );
			addPlayer( p.getName(), hash );
			AreasRegistry.updatePlayerChunk(p, hash );
		}
		
		AreasRegistry.updatePlayerMove(p);
		
	
		
	}
	
	public void close() {
		clear();
	}
	
	

}
