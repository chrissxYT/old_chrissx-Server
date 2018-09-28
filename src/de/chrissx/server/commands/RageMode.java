package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import de.chrissx.server.reflectors.GameMode;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.RageModeGame;
import de.chrissx.server.shared.Util;

public class RageMode extends CmdExec {
	
	public RageMode(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String lol_lel, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.RAGEMODE_COMMAND)) {
			if(!CommandChecker.check(s, true, false, 1, 1, args)) {
				return true;
			}
			CraftPlayer p = (CraftPlayer)s;
			Util.sendMsg(p, CC.GREEN, "Joining...");
			boolean joined = false;
			for(RageModeGame g : refl.getRageModeGames()) {
				if(g.getName().equalsIgnoreCase(args[0])) {
					joined = true;
					refl.setCurrentGame(p, GameMode.RAGEMODE);
					refl.setCurrentRageModeGame(p, g);
					refl.setInGame(p, true);
					g.onJoin(p);
				}
			}
			if(!joined) {
				Util.sendMsg(p, CC.RED, "Error!");
			}else {
				Util.sendMsg(p, CC.GREEN, "Joined RageMode-Game \"" + args[0] + "\".");
			}
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.RAGEMODE_COMMAND, this);
	}
}