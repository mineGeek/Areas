package com.github.mineGeek.Areas.Structs;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.entity.Player;

public class RegistryReset {
	
	AreaChunks areaChunks = new AreaChunks();
	public Map<String, Reset> resets = new WeakHashMap<String, Reset>();
	
	
	public void addReset( Reset reset ) {
		
		removeReset( reset );
		areaChunks.add( reset.area );
		
	}
	
	public void playerMove( Player p ) {

		Set<Area> areas = areaChunks.getByChunkHash( p.getLocation().getChunk().hashCode() );
		
		if ( areas != null ) {
			
			for ( Area a : areas ) { a.updatePlayerLocation(p); }
			
		}
		
	}
	
	public void playerMoveChunk( Player p, Integer oldHash, Integer newHash ) {

		Set<Area> set = new HashSet<Area>();
		Set<Area> add = null;
		
		add = areaChunks.getByChunkHash( oldHash );
		
		if ( add != null ) set.addAll( add );
		
		add = areaChunks.getByChunkHash( newHash );
		if ( add != null ) set.addAll( add );
		
		/*
		if ( !set.isEmpty() ) {
			for ( Area a : set ) {
				a.updatePlayerLocation(p);
				
			}
		}
		*/
		
	}
	
	public void removeReset( Reset reset ) {
		
		
		Area area = reset.area;
		areaChunks.remove( area );
		if ( resets.containsKey( reset.tag ) ) resets.remove( reset.tag );
		
	}
	
	
	
	public void close() {
		areaChunks.close();
		resets.clear();
	}
	
}
