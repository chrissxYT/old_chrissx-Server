package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.RageModeGame;
import de.chrissx.server.shared.Util;

public class RageModeStart extends CmdExec {
	
	public RageModeStart(Reflector refl) {
		super(refl);
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String ___, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.RAGEMODE_START_COMMAND)) {
			if(!(CommandChecker.check(s, true, true, 0, 0, args))) {
				return true;
			}
			Player p = (Player)s;
			if(!refl.isInRMGame(p)) {
				Util.sendMsg(p, CC.RED, "You are not in a game.");
				return true;
			}
			RageModeGame game = refl.getCurrentRageModeGame(p);
			game.setCountdownTime(10);
			server.getScheduler().runTaskAsynchronously(plugin, game);
			Util.sendMsg(p, CC.GREEN, "The game is now starting.");
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.RAGEMODE_START_COMMAND, this);
	}
}