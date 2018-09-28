package de.chrissx.server.events;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.Util;

public class CommandSpy extends Ev {
	
	private Reflector refl;
	
	public CommandSpy(Reflector refl) {
		this.refl = refl;
	}
	
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		Player sender = e.getPlayer();
		String cmd = e.getMessage();
			for(Player p : refl.getCMDSPYListeners()) {
				if(p != null) {
					Util.sendMsg(p, CC.GOLD, sender.getName() + " :: " + cmd);
				}
			}
		refl.getLogger().log(sender, cmd);
	}
	
	@Override
	public void init() {
		Util.registerEvents(this);
	}
}