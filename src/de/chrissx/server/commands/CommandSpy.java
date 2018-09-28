package de.chrissx.server.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.file.FileWriter;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.P;
import de.chrissx.server.shared.Util;

public class CommandSpy extends CmdExec {
	
	public CommandSpy(Reflector refl) {
		super(refl);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command c, String jeah, String[] args) {
		if(c.getName().equals(Commands.COMMANDSPY_LISTENER)) {
			if(!CommandChecker.check(s, false, true, 2, 2, args)) {
				return true;
			}
			Player p = server.getPlayer(args[0]);
			if(!(p != null)) {
				p = (Player) server.getOfflinePlayer(args[0]);
			}
			if(!(p != null)) {
				s.sendMessage("Player cannot be found.");
				return true;
			}
			boolean onoff = false;
			onoff = Boolean.parseBoolean(args[1]);
			File f = new File(P.CMDSPY_LISTENERS_PATH.toString(), p.getName() + P.CMDSPY_LISTENERS_EXTENTION);
			if(onoff) {
				if(!f.exists()) {
					try {
						Files.createFile(f.toPath());
					} catch (IOException e) {
						e.printStackTrace();
						return true;
					}
				}
				try {
					FileWriter.write(f, "true");
				} catch (Exception e) {
					e.printStackTrace();
				}
				refl.addCMDSPYListener(p);
			}else {
				if(f.exists()) {
					try {
						Files.delete(f.toPath());
					} catch (IOException e) {
						e.printStackTrace();
						return true;
					}
				}
			}
			Util.sendMsg(s, CC.GREEN, "Set CommandSpy for " + p.getName() + " to " + Boolean.toString(onoff));
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.COMMANDSPY_LISTENER, this);
	}
}