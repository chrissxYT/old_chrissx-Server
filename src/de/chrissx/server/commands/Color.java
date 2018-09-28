package de.chrissx.server.commands;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;
import de.chrissx.server.world.WorldUtil;

public class Color extends CmdExec {

	public Color(Reflector refl) {
		super(refl);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase("color")) {
			if(!CommandChecker.check(s, true, true, 2, 2, args)) {
				return true;
			}
			Player p = (Player)s;
			DyeColor dye;
			if(args[0].length() == 1 || args[0].length() == 2) {
				try {
					dye = DyeColor.getByData((byte)Integer.parseInt(args[0]));
				}catch(Exception e) {
					p.sendMessage(CC.RED + "Cannot parse dye: " + e.toString());
					return true;
				}
			}else {
				dye = DyeColor.valueOf(args[0]);
			}
			if(dye == null) {
				p.sendMessage(CC.RED + "Cannot get color.");
				return true;
			}
			int radius = 69;
			try {
				radius = Integer.parseInt(args[1]);
			}catch(Exception e) {
				p.sendMessage(CC.RED + "Error parsing radius: " + e.toString());
				return true;
			}
			for(Entity e : p.getNearbyEntities(radius, radius, radius)) {
				if(e instanceof Sheep) {
					((Sheep) e).setColor(dye);
				}
			}
			
			Location p_loc = p.getLocation(), sL = new Location(p_loc.getWorld(), p_loc.getBlockX() - radius, 255, p_loc.getBlockZ() - radius), eL = new Location(p_loc.getWorld(), p_loc.getBlockX() + radius, 1, p_loc.getBlockZ() + radius);
			
	        for(Block b : WorldUtil.blocksFromTwoPoints(sL, eL)) {
	        	if(b.getType().equals(Material.STAINED_CLAY) || b.getType().equals(Material.HARD_CLAY) || b.getType().equals(Material.CLAY) || b.getType().equals(Material.WOOL)) {
	        		b.setData(dye.getData());
	        	}
	        }
			
			Util.sendMsg(s, CC.GREEN, "Colored that sheeps, wools and clays");
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand("color", this);
	}

}