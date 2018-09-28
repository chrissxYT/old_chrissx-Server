package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.CommandError;
import de.chrissx.server.shared.Util;
import de.chrissx.server.skins.SkinUtil;

public class Nick extends CmdExec {
	
	public Nick(Reflector refl) {
		super(refl);
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String ___, String[] args) {
		if(c.getName().equalsIgnoreCase("nick")) {
			return nick(s, c, args);
		}else if(c.getName().equalsIgnoreCase("nickname")) {
			return nick(s, c, args);
		}
		return false;
	}
	
	private boolean nick(CommandSender s, Command c, String[] args) {
		if(!CommandChecker.check(s, true, false, 1, 2, args)) {
			return true;
		}
		
		Player p = (Player)s;
		if(args.length == 1) {
			p.setDisplayName(args[0].replaceAll("&", "§"));
			p.setPlayerListName(args[0].replaceAll("&", "§"));
			p.setCustomName(args[0].replaceAll("&", "§"));
			p.sendMessage("Nickname changed to \"" + args[0] + "\".");
			return true;
		}else {
			Player target = server.getPlayer(args[0]);
			if(p.isOp()) {
				if(target == null) {
					p.sendMessage(CC.RED + "Player \"" + args[0] + "\" cannot be found.");
					return true;
				}else {
					target.setDisplayName(args[1].replaceAll("&", "§"));
					target.setCustomName(args[0].replaceAll("&", "§"));
					target.setPlayerListName(args[0].replaceAll("&", "§"));
					p.sendMessage("Nickname of player \"" + target.getName() + "\" has been changed to \"" + args[1] + "\".");
					SkinUtil.updateAll();
					return true;
				}
			}else {
				Util.sendError(s, CommandError.NO_OP);
				return true;
			}
		}
	}

	@Override
	public void init() {
		Util.registerCommand("nick", this);
		Util.registerCommand("nickname", this);
	}
}