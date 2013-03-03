package com.github.mineGeek.Areas.Structs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.mineGeek.Areas.Events.EventController;
import com.github.mineGeek.Areas.Util.Players;

public class Area {

	public Location ne;
	public Location sw;
	public String worldName;
	public Set<Integer> chunkHashes = new HashSet<Integer>();
	public Set<String> players = new HashSet<String>();
	public Reset reset;
	
	public Area( Reset reset ) { this.reset = reset; }
	
	public void setNE( Location ne ) {
		
		if ( sw != null ) if ( !ne.getWorld().getName().equals(sw.getWorld().getName())) {
			//Error
		}
		if ( worldName == null ) worldName = ne.getWorld().getName();
		this.ne = ne;
		setChunkHashes();
	}
	
	public void setSW( Location sw ) {
		
		if ( ne != null ) if ( !ne.getWorld().getName().equals(ne.getWorld().getName())) {
			//Error
		}
		
		if ( worldName == null ) worldName = sw.getWorld().getName();
		this.sw = sw;
		setChunkHashes();
	}
	
	public Boolean intersectsWith( Location l ) {	
		
		if ( ( ne == null || sw == null ) && worldName != null) {
		
			if ( l.getWorld().getName().equalsIgnoreCase( this.worldName ) ) return true;
		
		} else {
		
			if ( l.getWorld().getName().equals( ne.getWorld().getName() ) ) {
				if ( ( Math.max( ne.getBlockX(), sw.getBlockX() ) < l.getBlockX() ) || ( Math.min( ne.getBlockX(), sw.getBlockX() ) > l.getBlockX() ) ) return false;
				if ( ( Math.max( ne.getBlockZ(), sw.getBlockZ() ) < l.getBlockZ() ) || ( Math.min( ne.getBlockZ(), sw.getBlockZ() ) > l.getBlockZ() ) ) return false;
				if ( ( Math.max( ne.getBlockY(), sw.getBlockY() ) < l.getBlockY() ) || ( Math.min( ne.getBlockY(), sw.getBlockY() ) > l.getBlockY() ) ) return false;	
				return true;
			} 
		}
		
		return false;
	}
	
	public Set<String> getPlayers() {
		
		return players;
		
	}
	
	public Set<Player> getPlayerObjects() {
		
		return Players.getPlayerObjectsFromList( players );
		
	}
	
	public void updatePlayerLocation( Player p ) {
		
		boolean inArea = isInArea( p );
		boolean wasInArea = players.contains( p.getName() );
		
		if ( !inArea && wasInArea ) {
			if ( EventController.PlayerExitArea(p, this ) ) players.remove( p.getName() );
			
		} else if ( !wasInArea && inArea ) {
			if ( EventController.PlayerEnterArea(p, this ) ) players.add( p.getName() );
		}
		
	}
	
	public boolean isInArea( Player p ) {
		return intersectsWith( p.getLocation() );
	}
	
	public boolean isInChunkHashes( Location l ) {
		return chunkHashes.contains( l.getChunk().hashCode() );
	}
	
	public Set<Integer> getChunkHashes() {
		return chunkHashes;
	}
	
	public void setChunkHashes() {
		
		chunkHashes.clear();
		if ( ne == null || sw == null ) return;
		
		List<Chunk> chunks = getChunks();
		
		for ( Chunk c : chunks ) {
			chunkHashes.add( c.hashCode() );
		}
		
	}
	
	public List<Chunk> getChunks() {
		
		List<Chunk> list = new ArrayList<Chunk>();
		
		int fromX = (int)Math.floor( Math.min( ne.getChunk().getX(), sw.getChunk().getX() ) );
		int toX =   (int)Math.ceil( Math.max( ne.getChunk().getX(), sw.getChunk().getX() ) );
		
		int fromZ = (int)Math.floor( Math.min( ne.getChunk().getZ(), sw.getChunk().getZ() ) );
		int toZ = (int)Math.ceil( Math.max( ne.getChunk().getZ(), sw.getChunk().getZ() ) );
		
		for( int x = fromX; x <= toX; x++ ) {
			
			for ( int z = fromZ; z <= toZ; z++) {
				list.add( ne.getWorld().getChunkAt(x, z) );
			}
			
		}		
		
		return list;
		
	}	
	
	public void close() {
		reset = null;
		sw = null;
		ne = null;
		chunkHashes.clear();
		players.clear();
	}
	
}
