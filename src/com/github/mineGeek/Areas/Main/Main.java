package com.github.mineGeek.Areas.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.mineGeek.Areas.Events.Listeners;
import com.github.mineGeek.Areas.Structs.Reset;


public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		
		Registry.close();
	}
	
	@Override
	public void onEnable() {
		
		
		Location ne1 = new Location( Bukkit.getWorld("pickleMasher"), 201, 61, 394);
		Location sw1 = new Location( Bukkit.getWorld("pickleMasher"), 185, 68, 383 );
		Reset reset1 = new Reset("ted");
		reset1.area.setNE(ne1);
		reset1.area.setSW(sw1);
		Registry.resets.addReset( reset1 );
		
    	/**
    	 * Listeners
    	 */
    	this.getServer().getPluginManager().registerEvents( new Listeners(), this);
    	
    	//Make some new zones for testing.
    	
    	
		Registry.plugin = this;
		
		for( Player p : getServer().getOnlinePlayers() ) {
			Registry.updatePlayerMove(p);
		}
	}
	
}
