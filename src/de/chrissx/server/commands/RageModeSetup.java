package de.chrissx.server.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.RageModeGame;
import de.chrissx.server.shared.Util;

public class RageModeSetup extends CmdExec {
	
	public RageModeSetup(Reflector refl) {
		super(refl);
		// TODO Auto-generated constructor stub
	}

	private Map<Player, Location[]> creatorArgs = new HashMap<Player, Location[]>();
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String _____, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.RAGEMODE_SETUP_COMMAND)) {
			if(!CommandChecker.check(s, true, true, 1, 1, args)) {
				return true;
			}
			Player p = (Player)s;
			if(args[0].equalsIgnoreCase("lobby")) {
				if(creatorArgs.containsKey(p)) {
					Location[] locs = creatorArgs.get(p);
					creatorArgs.remove(p);
					locs[0] = p.getLocation();
					creatorArgs.put(p, locs);
				}else {
					creatorArgs.put(p, new Location[] {p.getLocation(), new Location(p.getWorld(), 0, 0, 0), new Location(p.getWorld(), 0, 0, 0)});
				}
				Util.sendMsg(p, CC.GOLD, "Set lobby.");
			}else if(args[0].equalsIgnoreCase("arena")) {
				if(creatorArgs.containsKey(p)) {
					Location[] locs = creatorArgs.get(p);
					creatorArgs.remove(p);
					locs[1] = p.getLocation();
					creatorArgs.put(p, locs);
				}else {
					creatorArgs.put(p, new Location[] {new Location(p.getWorld(), 0, 0, 0), p.getLocation(), new Location(p.getWorld(), 0, 0, 0)});
				}
				Util.sendMsg(p, CC.GOLD, "Set arena.");
			}else if(args[0].equalsIgnoreCase("back")) {
				if(creatorArgs.containsKey(p)) {
					Location[] locs = creatorArgs.get(p);
					creatorArgs.remove(p);
					locs[2] = p.getLocation();
					creatorArgs.put(p, locs);
				}else {
					creatorArgs.put(p, new Location[] {new Location(p.getWorld(), 0, 0, 0), new Location(p.getWorld(), 0, 0, 0), p.getLocation()});
				}
				Util.sendMsg(p, CC.GOLD, "Set back.");
			}else {
				refl.addRageModeGame(new RageModeGame(args[0], creatorArgs.get(p)[0], creatorArgs.get(p)[1], creatorArgs.get(p)[2]));
				Util.sendMsg(p, CC.GOLD, "Added arena.");
			}
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.RAGEMODE_SETUP_COMMAND, this);
	}
}