package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.Util;

public class VanillaBlocker extends CmdExec {

	public VanillaBlocker(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String LOL_LEL_KEK, String[] args) {
		if(c.getName().equalsIgnoreCase("help")) {
			if(!s.isOp()) {
				Util.sendMsg(s, CC.RED, "Nö. :P");
				return true;
			}
			String a = "";
			for(String as : args) {
				a+=" "+as;
			}
			server.dispatchCommand(s, "bukkit:help"+a);
			return true;
		}else if(c.getName().equalsIgnoreCase("pl")) {
			if(!s.isOp()) {
				Util.sendMsg(s, CC.RED, "Nö. :P");
			}
			String a = "";
			for(String as : args) {
				a+=" "+as;
			}
			server.dispatchCommand(s, "bukkit:pl"+a);
			return true;
		}else if(c.getName().equalsIgnoreCase("plugins")) {
			if(!s.isOp()) {
				Util.sendMsg(s, CC.RED, "Nö. :P");
			}
			String a = "";
			for(String as : args) {
				a+=" "+as;
			}
			server.dispatchCommand(s, "bukkit:plugins"+a);
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand("help", this);
		Util.registerCommand("pl", this);
		Util.registerCommand("plugins", this);
	}
}