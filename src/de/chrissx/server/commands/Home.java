package de.chrissx.server.commands;

import java.io.File;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.file.FileLoader;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.P;
import de.chrissx.server.shared.Util;

public class Home extends CmdExec {
	
	public Home(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase("home")) {
			if(!CommandChecker.check(s, true, false, 0, 1, args)) {
				return true;
			}
			Player p = (Player)s;
			File homeFile;
			homeFile = new File(P.PLAYER_HOMES.toString(), p.getName() + P.PLAYER_HOMES_EXTENTION);
			if(!homeFile.exists()) {
				p.sendMessage(CC.RED + "You don't have a home to go to.");
				return true;
			}
			List<String> homeList;
			try {
				homeList = FileLoader.getText(homeFile.toPath());
			} catch (Exception e) {
				e.printStackTrace();
				p.sendMessage(CC.RED + "Internal error(see console for information)");
				return true;
			}
			int x = Integer.parseInt(homeList.get(0)), y = Integer.parseInt(homeList.get(1)), z = Integer.parseInt(homeList.get(2));
			World w = server.getWorld(homeList.get(3));
			Location tploc = new Location(w, x, y, z);
			if(tploc.getBlock().getType().isSolid() && args.length == 0) {
				p.sendMessage("The location is in the ground, if you still want to be teleported, you must enter /home [any letter you want]");
				return true;
			}
			p.teleport(tploc);
			Util.sendMsg(p, CC.GREEN, "You have been teleported.");
			return true;
			
			
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand("home", this);
	}
}