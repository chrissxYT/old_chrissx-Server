package de.chrissx.server.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.chrissx.playerlist.PlayerUtil;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class TeleportWorld extends CmdExec {
	
	public TeleportWorld(Reflector refl) {
		super(refl);
		// TODO Auto-generated constructor stub
	}

	private Plugin plugin = Util.getPlugin();

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase("teleport")) {
			if(!(CommandChecker.check(s, true, true, 1, 4, args))) {
				return true;
			}
			
			Player p = (Player)s;
			PlayerUtil.tpByWorldManager(args, p, plugin);
		}else if(c.getName().equalsIgnoreCase("worldlist")) {
			
			
			s.sendMessage("List of worlds:");
			for(World w : server.getWorlds()) {
				s.sendMessage(w.getName());
			}
			
			
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand("teleport", this);
	}
}