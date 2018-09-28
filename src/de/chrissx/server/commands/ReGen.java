package de.chrissx.server.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.P;
import de.chrissx.server.shared.Util;
import de.chrissx.server.world.WorldUtil;

public class ReGen extends CmdExec {
	
	public ReGen(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase("regen")) {
			
			
			if(!(CommandChecker.check(s, true, true, 0, 1, args))) {
				return true;
			}
			
			Player p = (Player)s;
			World w = p.getWorld();
			
			if(args.length == 1) {
				w = server.getWorld(args[0]);
				if(!(w != null)) {
					p.sendMessage(CC.RED + "World cannot be found.");
					return true;
				}
			}
			
			if(w == Bukkit.getWorlds().get(0)) {
				Util.sendMsg(p, CC.RED, "The default cannot be regenerated.");
				return true;
			}
			
			try {
				String path;
				for(Player p1 : w.getPlayers()) {
					p1.kickPlayer("You were kicked because the world you were in is being regenerated, please reconnect in a few seconds.");
				}
				Bukkit.unloadWorld(w, true);
				path = P.SERVER_FOLDER + File.separator + w.getName() + File.separator + "region";
				if(WorldUtil.deleteWorld(new File(path))) {
					System.out.println("Deleted: " + path);
				}else {
					System.out.println("Cannot delete region-folder \"" + path + "\"!");
				}
				Bukkit.dispatchCommand(server.getConsoleSender(), "reload");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand("regen", this);
	}

}
