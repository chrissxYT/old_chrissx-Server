package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.GameMode;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.CommandError;
import de.chrissx.server.shared.Util;

public class Stats extends CmdExec {
	
	public Stats(Reflector refl) {
		super(refl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase("stats")) {
			if(!CommandChecker.check(s, true, false, 0, 1, args)) {
				return true;
			}
			Player p = (Player)s;
			if(args.length == 0 && !refl.isInGame(p)) {
				Util.sendMsg(p, CC.RED, "You are not in a game, so use \"/stats [Game]\".");
				return true;
			}
			if(args.length == 0) {
				stats(refl.getCurrentGame(p), p);
			}else {
				GameMode g = GameMode.valueOf(args[0].toUpperCase());
				if(g == null) {
					Util.sendError(p, CommandError.ENUM_ERROR);
					return true;
				}
				stats(g, p);
			}
			return true;
		}
		return false;
	}
	
	private void stats(GameMode g, Player p) {
		switch(g) {
		case RAGEMODE: Util.getServer().dispatchCommand(p, "rmstats"); break;
		case BEDWARS: Util.getServer().dispatchCommand(p, "bwstats"); break;
		case NONE: Util.sendError(p, CommandError.INTERNAL, "I don't think, this is really internal, I think, you just entered \"none\" as the argument."); break;
		case SKYWARS: Util.getServer().dispatchCommand(p, "swstats"); break;
		case SURVIVAL_GAMES: Util.getServer().dispatchCommand(p, "sgstats"); break;
		default: Util.sendError(p, CommandError.NOT_ADDED_TO_SWITCH_CASE, this.getClass().getName()+":44-51"); break;
		}
	}

	@Override
	public void init() {
		Util.registerCommand("stats", this);
	}
}