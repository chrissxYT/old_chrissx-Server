package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class Ip extends CmdExec {
	
	public Ip(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String __LOL__LEL__KEK__, String[] args) {
		if(c.getName().equalsIgnoreCase("ip")){
			return ip(s, c, args, "IP");
		}else if(c.getName().equalsIgnoreCase("address")) {
			return ip(s, c, args, "Address");
		}
		return false;
	}

	private boolean ip(CommandSender s, Command c, String[] args, String name) {
		if(!CommandChecker.check(s, false, true, 1, 1, args)) {
			return true;
		}
		Player target = server.getPlayer(args[0]);
		if(target == null) {
			s.sendMessage(CC.RED + "Player \"" + args[0] + "\" could not be found.");
			return true;
		}
		Util.sendMsg(s, CC.GOLD, target.getDisplayName() + "'s " + name + " is " + target.getAddress().getAddress().toString().substring(1));
		return true;
		
	}

	@Override
	public void init() {
		Util.registerCommand("ip", this);
		Util.registerCommand("address", this);
	}
}