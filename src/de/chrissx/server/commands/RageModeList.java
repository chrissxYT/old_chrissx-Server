package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.RageModeGame;
import de.chrissx.server.shared.Util;

public class RageModeList extends CmdExec {
	public RageModeList(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String ____, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.RAGEMODE_LIST_COMMAND)) {
			Util.sendMsg(s, CC.GOLD, "List of RageMode-Games:");
			for(RageModeGame g : refl.getRageModeGames()) {
				Util.sendMsg(s, CC.GOLD, g.getName());
			}
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.RAGEMODE_LIST_COMMAND, this);
	}
}