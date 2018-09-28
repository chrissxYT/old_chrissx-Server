package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class Ping extends CmdExec {

	public Ping(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String ____, String[] args) {
		if(c.getName().equalsIgnoreCase("ping")) {
			if(!CommandChecker.check(s, false, false, 0, 1, args)) {
				return true;
			}
			
			Player p;
			
			if(args.length == 0) {
				p = (Player)s;
			}else {
				p = server.getPlayer(args[0]);
			}
			
			if(p == null) {
				s.sendMessage(CC.RED + "Player cannot be found.");
				return true;
			}
			
			Util.sendMsg(s, CC.GOLD, p.getName() + "'s ping: " + (Util.getPing(p)));
			return true;
		}
		return false;

	}

	@Override
	public void init() {
		Util.registerCommand("ping", this);
	}
}