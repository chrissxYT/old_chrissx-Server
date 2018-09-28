package de.chrissx.RageMode;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowHitListener implements Listener {

	public static final float EXPLOSION_POWER = 5.0f;

	@EventHandler
	public void onArrowHit(ProjectileHitEvent e) {
		if(!(e.getEntity() instanceof Arrow)) {
			return;
		}
		
		Arrow arrow = (Arrow) e.getEntity();
		Location loc = arrow.getLocation();
		
		loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), EXPLOSION_POWER, false, false);
	}
}