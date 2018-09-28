package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class DeleteWorld extends CmdExec {
	
	public DeleteWorld(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase("delete")) {
			
			
			if(!CommandChecker.check(s, false, true, 1, 1, args)) {
				return true;
			}
			try {
				server.getWorld(args[0]).getWorldFolder().delete();
			}catch(Exception e) {
				Util.sendMsg(s, CC.RED, "Error: " + e.getMessage());
			}
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand("delete", this);
	}
}