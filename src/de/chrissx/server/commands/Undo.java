package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;
import de.chrissx.server.world.WorldUtil;

public class Undo extends CmdExec {
	
	public Undo(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String _LEL_, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.UNDO_COMMAND)) {
			if(!CommandChecker.check(s, true, true, 0, 1, args)) {
				return true;
			}
			
			Player p = (Player)s;
			
			int count;
			if(args.length == 0) {
				count = 1;
			}else {
				try {
					count = Integer.parseInt(args[0]);
				}catch(Exception e) {
					e.printStackTrace();
					Util.sendMsg(p, CC.RED, "Are you sure you entered a number?");
					return true;
				}
			}
			WorldUtil.undoActions(p, count, refl);
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.UNDO_COMMAND, this);
	}
}