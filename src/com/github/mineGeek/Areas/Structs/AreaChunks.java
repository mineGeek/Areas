package com.github.mineGeek.Areas.Structs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class AreaChunks {
	
	public Map<Integer, Set<Area>> chunkToArea = new WeakHashMap<Integer, Set<Area>>();
	public Map<Area, Set<Integer>> areaToChunk = new WeakHashMap<Area, Set<Integer>>();
	
	public void add( Integer chunkHash, Area area ) {
		
		remove( area );
		
		if ( !areaToChunk.containsKey( area ) ) {
			areaToChunk.put( area, new HashSet<Integer>() );
		}
		
		if ( !chunkToArea.containsKey( chunkHash ) ) {
			chunkToArea.put( chunkHash, new HashSet<Area>() );
		}
		
		areaToChunk.get( area ).add( chunkHash );
		chunkToArea.get( chunkHash ).add( area );
		
		
	}
	
	public void add( Area area, Set<Integer> chunkHashes ) {
		
		remove( area );
		
		if ( !areaToChunk.containsKey( area ) ) {
			areaToChunk.put( area,  chunkHashes );
		} else {
			areaToChunk.get( area ).addAll( chunkHashes );
		}
		
		for ( Integer x : chunkHashes ) {
			if ( !chunkToArea.containsKey( x ) ) {
				chunkToArea.put( x, new HashSet<Area>( Arrays.asList( area ) ) );
			} else {
				chunkToArea.get( x ).add( area );
			}
		}
		
		
		
	}
	
	public void add( Area area ) {
		
		Set<Integer> set = area.getChunkHashes();
		add( area, set );
		
	}
	
	public void remove( Area area ) {
		
		Set<Integer> set = areaToChunk.get( area );
		if ( set != null ) {
			for( Integer x : set ) {
				chunkToArea.get( x ).remove( area );
			}
		}
		
		areaToChunk.remove( area );
		
	}
	
	public Set<Area> getByChunkHash( Integer hash ) {
		
		return chunkToArea.get( hash );
		
	}
	
	public Set<Integer> getByArea( Area area ) {
		return areaToChunk.get( area );
	}
	
	public void close() {
		chunkToArea.clear();
		areaToChunk.clear();
	}
	
}
