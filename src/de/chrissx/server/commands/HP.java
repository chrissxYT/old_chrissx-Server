package de.chrissx.server.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.playerlist.PlayerUtil;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class HP extends CmdExec {
	
	public HP(Reflector refl) {
		super(refl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String __USELESS_SHIT__, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.HP_COMMAND)) {
			if(!CommandChecker.check(s, false, true, 1, 2, args)) {
				return true;
			}
			if(!(s instanceof Player) && args.length < 2) {
				Util.sendMsg(s, CC.RED, "If you aren't a player you must enter a player's name!");
				return true;
			}
			Player p;
			int i;
			if(args.length == 1) {
				try {
					i = Integer.parseInt(args[0]);
					p = (Player)s;
				}catch(Exception e) {
					e.printStackTrace();
					Util.sendMsg(s, CC.RED, e.getMessage());
					return true;
				}
			}else {
				try {
					p = Bukkit.getPlayer(args[1]);
					i = Integer.parseInt(args[0]);
				}catch(Exception e) {
					e.printStackTrace();
					Util.sendMsg(s, CC.RED, e.getMessage());
					return true;
				}
			}
			PlayerUtil.setMaxHealth(p, i);
			Util.sendMsg(s, CC.GREEN, "Set " + p.getName() + "'s max. HP to " + Integer.toString(i) + ".");
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.HP_COMMAND, this);
	}
}