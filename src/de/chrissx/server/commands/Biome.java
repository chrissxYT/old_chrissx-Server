package de.chrissx.server.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.blockedit.editactions.EditActionBiome;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;
import de.chrissx.server.skins.SkinUtil;
import de.chrissx.server.world.WorldUtil;

public class Biome extends CmdExec {

	public Biome(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String ___, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.BIOME_COMMAND)) {
			if(!CommandChecker.check(s, true, true, 1, 1, args)) {
				return true;
			}
			
			Player p = (Player)s;
			Location loc1, loc2;
			org.bukkit.block.Biome b;
			List<org.bukkit.block.Biome> before = new ArrayList<org.bukkit.block.Biome>();
			try {
				loc1 = refl.getLoc1(p);
				loc2 = refl.getLoc2(p);
			}catch(Exception e) {
				Util.sendMsg(p, CC.RED, "One of your points is null!");
				return true;
			}
			try {
				b = org.bukkit.block.Biome.valueOf(args[0].toUpperCase());
			}catch(Exception e) {
				Util.sendMsg(p, CC.RED, "Cannot parse your Biome.");
				return true;
			}
			
			List<Block> blocks = WorldUtil.blocksFromTwoPoints(new Location(loc1.getWorld(), loc1.getBlockX(), 1, loc1.getBlockZ()), new Location(loc2.getWorld(), loc2.getBlockX(), 255, loc2.getBlockZ()));
			
			for(Block bl : blocks) {
				Location l = bl.getLocation();
				before.add(loc1.getWorld().getBiome(l.getBlockX(), l.getBlockZ()));
				loc1.getWorld().setBiome(l.getBlockX(), l.getBlockZ(), b);
			}
			
			refl.addAction(new EditActionBiome(blocks, before, p.getName()));
			SkinUtil.updateAll();
			Util.sendMsg(p, CC.GREEN, "Changed biomes of the blocks.");
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.BIOME_COMMAND, this);
	}

}