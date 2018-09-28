package de.chrissx.server.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.file.FileWriter;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.P;
import de.chrissx.server.shared.Util;

public class SetHome extends CmdExec {
	
	public SetHome(Reflector refl) {
		super(refl);
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String FU, String[] args) {
		if(c.getName().equalsIgnoreCase("sethome")) {
			if(!CommandChecker.check(s, true, false, 0, 4, args)) {
				return true;
			}
			
			Player p = (Player)s;
			File homeFile;
			homeFile = new File(P.PLAYER_HOMES.toString(), p.getName() + P.PLAYER_HOMES_EXTENTION);
			if(!homeFile.exists()) {
				try {
					Files.createFile(homeFile.toPath());
				} catch (IOException e) {
					e.printStackTrace();
					p.sendMessage(CC.RED + "Error creating your home-file.");
					return true;
				}
			}
			Location homeLoc;
			if(args.length == 4) {
				try {
					homeLoc = new Location(server.getWorld(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				}catch(Exception e) {
					e.printStackTrace();
					p.sendMessage(CC.RED + "Error parsing command /sethome OR /sethome [world] [x] [y] [z]");
					return true;
				}
			}else {
				homeLoc = p.getLocation();
			}
			String toWrite = Integer.toString(homeLoc.getBlockX()) + "\n" + Integer.toString(homeLoc.getBlockY()) + "\n" + Integer.toString(homeLoc.getBlockZ()) + "\n" + homeLoc.getWorld().getName();
			try {
				FileWriter.write(homeFile, toWrite);
				p.sendMessage(CC.GREEN + "Saved home.");
			} catch (Exception e) {
				e.printStackTrace();
				Util.sendMsg(p, CC.RED, "Error saving home: " + e.getMessage());
				return true;
			}
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand("sethome", this);
	}
}