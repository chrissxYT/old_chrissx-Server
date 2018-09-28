package de.chrissx.server.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.blockedit.BlockEditLogAction;
import de.chrissx.server.blockedit.BlockEditLogActionType;
import de.chrissx.server.blockedit.editactions.EditActionSet;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.CommandError;
import de.chrissx.server.shared.Util;
import de.chrissx.server.world.WorldUtil;

public class Set extends CmdExec {

	public Set(Reflector refl) {
		super(refl);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command c, String _XOXOXOXOXO_, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.SET_COMMAND)) {
			if(!(CommandChecker.check(s, true, true, 1, 2, args))) {
				return true;
			}
			Player p = (Player)s;
			Material m = Material.STONE;
			Location loc1 = null, loc2 = null;
			try {
				loc1 = refl.getLoc1(p);
				loc2 = refl.getLoc2(p);
			}catch(Exception e) {
				Util.sendMsg(p, CC.RED, "You haven't set your positions right!");
				return true;
			}
			try {
				m = Material.getMaterial(Integer.parseInt(args[0]));
			}catch(Exception e) {
				try {
					m = Material.valueOf(args[0].toUpperCase());
				}catch(Exception e1) {
					Util.sendError(p, CommandError.ENUM_ERROR, e1.getMessage());
					return true;
				}
			}
			byte b = 0;
			if(args.length == 2){
				try {
					b = Byte.parseByte(args[1]);
				}catch(Exception e) {
					Util.sendMsg(p, CC.RED, "Error parsing byte: " + e.getMessage());
					return true;
				}
			}
			
			List<Block> blocks = WorldUtil.blocksFromTwoPoints(loc1, loc2);
			List<Material> before = new ArrayList<Material>();
			for(Block block : blocks) {
				before.add(block.getType());
				block.setType(m);
				block.setData(b);
			}
			
			refl.addAction(new EditActionSet(blocks, before, p.getName()));
			refl.getLogger().log(new BlockEditLogAction(BlockEditLogActionType.SET, m, p, refl.getLoc1(p)));
			Util.sendMsg(p, CC.GOLD, "Set "+blocks.size()+" blocks.");
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.SET_COMMAND, this);
	}
}