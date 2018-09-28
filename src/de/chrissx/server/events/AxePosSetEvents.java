package de.chrissx.server.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.chrissx.server.blockedit.BlockEditLogAction;
import de.chrissx.server.blockedit.BlockEditLogActionType;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.Util;

public class AxePosSetEvents extends Ev {
	
	private Reflector refl;
	
	public AxePosSetEvents(Reflector refl) {
		this.refl = refl;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getItem() == null) {
			return;
		}
		if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if(e.getItem().equals(new ItemStack(Material.WOOD_AXE, 1))) {
				Player p = e.getPlayer();
				refl.setLoc1(p, e.getClickedBlock().getLocation());
				e.setCancelled(true);
				refl.getLogger().log(new BlockEditLogAction(BlockEditLogActionType.POS1_AXE, e.getClickedBlock().getType(), p, p.getLocation()));
			}
		}else if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getItem().equals(new ItemStack(Material.WOOD_AXE, 1))) {
				Player p = e.getPlayer();
				refl.setLoc2(p, e.getClickedBlock().getLocation());
				e.setCancelled(true);
				refl.getLogger().log(new BlockEditLogAction(BlockEditLogActionType.POS2_AXE, e.getClickedBlock().getType(), p, p.getLocation()));
			}
		}
	}

	@Override
	public void init() {
		Util.registerEvents(this);
	}
}
