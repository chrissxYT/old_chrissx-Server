package de.chrissx.server.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.chrissx.server.shared.Util;

public class PlayerDeathListener extends Ev {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.setKeepInventory(true);
		e.setDeathMessage("");
		e.setDroppedExp(0);
		e.setKeepLevel(true);
	}
	
	@Override
	public void init() {
		Util.registerEvents(this);
	}
}