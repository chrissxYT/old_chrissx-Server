package de.chrissx.server.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class WorldList extends CmdExec {

	public WorldList(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String _UIOPKOJP_, String[] args) {
		if(c.getName().equalsIgnoreCase("worldlist")) {
			if(!CommandChecker.check(s, false, true, 0, 0, args)) {
				return true;
			}
			Util.sendMsg(s, CC.YELLOW, "Worlds:");
			for(World w : Bukkit.getWorlds()) {
				Util.sendMsg(s, CC.GOLD, w.getName());
			}
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand("worldlist", this);
	}

}