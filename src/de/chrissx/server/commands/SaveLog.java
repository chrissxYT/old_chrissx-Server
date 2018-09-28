package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class SaveLog extends CmdExec {
	public SaveLog(Reflector refl) {
		super(refl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String ____, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.SAVE_LOG)){
			if(!CommandChecker.check(s, false, true, 0, 0, args)) {
				return true;
			}
			String path;
			try {
				path = refl.getLogger().writeLog();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			Util.sendMsg(s, CC.GREEN, "Saved Log to \"" + path + "\".");
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.SAVE_LOG, this);
	}
}