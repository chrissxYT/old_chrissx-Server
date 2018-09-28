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
import de.chrissx.server.blockedit.editactions.EditActionWalls;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.CommandError;
import de.chrissx.server.shared.Util;
import de.chrissx.server.world.WorldUtil;

public class Walls extends CmdExec {

	public Walls(Reflector refl) {
		super(refl);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command c, String _KEK_, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.WALLS_COMMAND)) {
			if(!(CommandChecker.check(s, true, true, 2, 3, args))) {
				return true;
			}
			try {
				
				int radius = Integer.parseInt(args[0]);
				Player p = (Player)s;
				Location l = p.getLocation();
				byte d = 0;
				Material mat;
				
				try {
					mat = Material.getMaterial(Integer.parseInt(args[1]));
				}catch(Exception e) {
					mat = Material.getMaterial(args[1].toUpperCase());
				}
				
				if(args.length == 3) {
					try {
						d = Byte.parseByte(args[2]);
					}catch(Exception e) {
						e.printStackTrace();
						Util.sendMsg(p, CC.RED, "Error parsing data: " + e.getMessage());
						return true;
					}
				}
				
				List<Block> blocks = WorldUtil.getWalls(l, radius);
				List<Material> before = new ArrayList<Material>();
				
				for(Block b : blocks) {
					before.add(b.getType());
					b.setType(mat);
					b.setData(d);
				}
				refl.addAction(new EditActionWalls(blocks, before, p.getName()));
				Util.sendMsg(s, CC.GOLD, "Walls set.");
				refl.getLogger().log(new BlockEditLogAction(BlockEditLogActionType.WALLS, mat, p, l));
				return true;
				
			}catch(Exception e) {
				e.printStackTrace();
				Util.sendError(s, CommandError.WRONG_USAGE);
				return true;
			}
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.WALLS_COMMAND, this);
	}

}