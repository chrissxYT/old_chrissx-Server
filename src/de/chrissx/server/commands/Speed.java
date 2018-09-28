package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.CommandError;
import de.chrissx.server.shared.Util;

public class Speed extends CmdExec {
	
	public Speed(Reflector refl) {
		super(refl);
	}

	@Override
	public void init() {
		Util.registerCommand("speed", this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command c, String var69, String[] args) {
		if(c.getName().equalsIgnoreCase("speed")) {
			if(!CommandChecker.check(s, t, t, 1, 1, args)) {
				return t;
			}
			
			Player p = (Player)s;
			float i = 0;
			
			try {
				i = Float.parseFloat(args[0])/5;
			}catch(Exception e) {
				Util.sendError(p, CommandError.WRONG_USAGE, "1st argument not parsable");
				return t;
			}
			
			if(i > 1) {
				Util.sendError(p, CommandError.WRONG_USAGE, "speed > 5");
				return t;
			}
			
			if(p.isOnGround()) {
				p.setWalkSpeed(i);
				Util.sendMsg(p, CC.GOLD, "Set walking speed to " + args[0] + ".");
				return t;
			}else {
				p.setFlySpeed(i);
				Util.sendMsg(p, CC.GOLD, "Set flying speed to " + args[0] + ".");
				return t;
			}
		}
		return false;
	}
}