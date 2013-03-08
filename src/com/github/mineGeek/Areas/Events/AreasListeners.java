package com.github.mineGeek.Areas.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.github.mineGeek.Areas.Main.AreasRegistry;
import com.github.mineGeek.Areas.Structs.Area.PVPMode;


public class AreasListeners implements Listener {

	/**
	 * Handles PVP toggling in area.
	 * @param evt
	 */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamage(EntityDamageByEntityEvent evt) {
    	
    	if ( evt.isCancelled() ) return;
    	
    	if ( evt.getDamager() instanceof Player ) {
    	
    		Player player = (Player)evt.getDamager();
    		
    		PVPMode mode = AreasRegistry.players.getPlayerPVPMode( player );
    		if ( mode == PVPMode.OFF ) evt.setCancelled( true );
    	}
    	
    }	
	
    /**
     * Registers players location within areas when they join
     * @param evt
     */
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin( PlayerJoinEvent evt ) {
		AreasRegistry.updatePlayerLocation( evt.getPlayer() );
	}
	
	/**
	 * Updates players last location when they leave
	 * @param evt
	 */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLeave(PlayerQuitEvent evt ) {
    	AreasRegistry.updatePlayerLocation( evt.getPlayer() );
    }
    
    /**
     * Updates players location within system when they move a block
     * @param evt
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerMove( PlayerMoveEvent evt ) {
    	if ( evt.getFrom().getBlock().equals( evt.getTo().getBlock())) return;
    	AreasRegistry.updatePlayerLocation( evt.getPlayer() );
    }
    
    /**
     * Updates players location within system when they change worlds
     * @param evt
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChangedWorld(PlayerChangedWorldEvent evt) {
    	AreasRegistry.updatePlayerLocation( evt.getPlayer() );
    }
    
    /**
     * Updates players location within system when they respawn
     * @param evt
     */
	@EventHandler(priority = EventPriority.LOWEST )
    public void onRespawn(PlayerRespawnEvent evt) {
		AreasRegistry.updatePlayerLocation( evt.getPlayer() );
	}
	
}
