package com.github.mineGeek.Areas.Structs;

import org.bukkit.entity.Player;

public interface IAreaEventsHandler {
	
	public void playerMoveChunk( Player p, Integer oldHash, Integer newHash );
	public void playerMove( Player p );
}
