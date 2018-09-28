package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class Broadcast extends CmdExec {

	public Broadcast(Reflector refl) {
		super(refl);
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] a) {
		if(c.getName().equalsIgnoreCase("broadcast")) {
			broadcast(s, c, a);
		}else if(c.getName().equalsIgnoreCase("bc")) {
			broadcast(s, c, a);
		}
		return false;
	}

	private boolean broadcast(CommandSender s, Command c, String[] args) {
		if(!CommandChecker.check(s, false, true, 2, Integer.MAX_VALUE, args)) {
			return true;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(args[0].replace("&", "§"));
		builder.append("[");
		builder.append(args[1].replace("&", "§"));
		builder.append("]§f ");
		for(int i = 2; i < args.length; i++) {
			builder.append(args[i].replace("&", "§") + " ");
		}
		server.broadcastMessage(builder.toString());
		return true;
	}

	@Override
	public void init() {
		Util.registerCommand("broadcast", this);
		Util.registerCommand("bc", this);
	}
}