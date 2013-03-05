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

import com.github.mineGeek.Areas.Main.Registry;
import com.github.mineGeek.Areas.Structs.Area.PVPMode;


public class AreaListeners implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamage(EntityDamageByEntityEvent evt) {
    	
    	if ( evt.isCancelled() ) return;
    	
    	if ( evt.getDamager() instanceof Player ) {
    	
    		Player player = (Player)evt.getDamager();
    		
    		PVPMode mode = Registry.players.getPlayerPVPMode( player );
    		if ( mode == PVPMode.OFF ) evt.setCancelled( true );
    	}
    	
    }	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin( PlayerJoinEvent evt ) {
		Registry.updatePlayerLocation( evt.getPlayer() );
	}
	
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLeave(PlayerQuitEvent evt ) {
    	Registry.updatePlayerLocation( evt.getPlayer() );
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerMove( PlayerMoveEvent evt ) {
    	if ( evt.getFrom().getBlock().equals( evt.getTo().getBlock())) return;
    	Registry.updatePlayerLocation( evt.getPlayer() );
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChangedWorld(PlayerChangedWorldEvent evt) {
    	Registry.updatePlayerLocation( evt.getPlayer() );
    }
    
	@EventHandler(priority = EventPriority.LOWEST )
    public void onRespawn(PlayerRespawnEvent evt) {
		Registry.updatePlayerLocation( evt.getPlayer() );
	}
	
}
