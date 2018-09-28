package de.chrissx.server.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.CommandError;
import de.chrissx.server.shared.Util;

public class AntiGriefListener extends CmdExec {

	public AntiGriefListener(Reflector refl) {
		super(refl);
	}

	@Override
	public void init() {
		Util.registerCommand("agl", this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command c, String var69, String[] args) {
		if(c.getName().equalsIgnoreCase("agl")) {
			if(!CommandChecker.check(s, false, true, 0, 1, args)) {
				return true;
			}
			if(args.length == 0 && !(s instanceof Player)) {
				Util.sendError(s, CommandError.NO_PLAYER, "If you aren't a player, please enter a player as an argument!");
				return true;
			}
			Player p;
			if(args.length == 0) {
				p = (Player)s;
			}else {
				p = Bukkit.getPlayer(args[0]);
				if(p == null) {
					p = Bukkit.getOfflinePlayer(args[0]).getPlayer();
					if(p == null) {
						Util.sendError(s, CommandError.PLAYER_NOT_FOUND);
						return true;
					}
				}
			}
			refl.addAGListener(p);
			Util.sendMsg(s, CC.GREEN, "Player \"" + p.getName() + "\" added.");
			return true;
		}
		return false;
	}

}