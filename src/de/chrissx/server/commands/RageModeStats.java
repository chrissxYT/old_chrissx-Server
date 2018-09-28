package de.chrissx.server.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.P;
import de.chrissx.server.shared.Util;

public class RageModeStats extends CmdExec {
	public RageModeStats(Reflector refl) {
		super(refl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String ____, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.RAGEMODE_STATS_COMMAND)) {
			if(!(CommandChecker.check(s, true, false, 0, 0, args))) {
				return true;
			}
			Player p = (Player)s;
			Path path = Paths.get(P.RAGEMODE_STATS.toString(), p.getName() + P.RAGEMODE_STATS_EXTENTION);
			if(!path.toFile().exists()) {
				Util.sendMsg(p, CC.RED, "You don't have any stats in RageMode.");
				return true;
			}
			
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.RAGEMODE_STATS_COMMAND, this);
	}
}