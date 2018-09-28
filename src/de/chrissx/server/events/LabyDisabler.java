package de.chrissx.server.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import de.chrissx.server.shared.LabyUtil;
import de.chrissx.server.shared.Util;

public class LabyDisabler extends Ev {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(e.getPlayer().getName() != "chrissxYT") {
			LabyUtil.setLabySettings(e.getPlayer());
			System.out.println("Disabled labymod for " + e.getPlayer().getName() + ".");
		}
	}
	
	@Override
	public void init() {
		Util.registerEvents(this);
	}
}