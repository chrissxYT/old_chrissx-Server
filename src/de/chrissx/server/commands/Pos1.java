package de.chrissx.server.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.blockedit.BlockEditLogAction;
import de.chrissx.server.blockedit.BlockEditLogActionType;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class Pos1 extends CmdExec {

	public Pos1(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String _KEK_, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.POSITION_ONE_COMMAND)) {
			if(!(CommandChecker.check(s, true, true, 0, 0, args))) {
				return true;
			}
			Player p = (Player)s;
			refl.setLoc1(p, p.getLocation());
			refl.getLogger().log(new BlockEditLogAction(BlockEditLogActionType.POS1_CMD, Material.AIR, p, p.getLocation()));
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.POSITION_ONE_COMMAND, this);
	}
}