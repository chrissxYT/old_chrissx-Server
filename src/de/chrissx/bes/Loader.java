package de.chrissx.bes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import de.chrissx.server.blockedit.editactions.EditAction;
import de.chrissx.server.blockedit.editactions.EditActionBiome;
import de.chrissx.server.blockedit.editactions.EditActionSet;
import de.chrissx.server.blockedit.editactions.EditActionType;
import de.chrissx.server.blockedit.editactions.EditActionWalls;
import de.chrissx.server.file.FileLoader;
import de.chrissx.server.file.FileUtil;
import de.chrissx.server.world.WorldUtil;

public class Loader {

	public synchronized static List<EditAction> loadActions(File f, String ext) throws IOException {
		List<EditAction> out = new ArrayList<EditAction>();
		for(int i = 0; i < FileUtil.countLines(f); i+=6) {
			List<String> load = new ArrayList<String>();
			load.add(FileLoader.getLine(f.toPath(), i+0));
			load.add(FileLoader.getLine(f.toPath(), i+1));
			load.add(FileLoader.getLine(f.toPath(), i+2));
			load.add(FileLoader.getLine(f.toPath(), i+3));
			load.add(FileLoader.getLine(f.toPath(), i+4));
			load.add(FileLoader.getLine(f.toPath(), i+5));
			out.add(besLoad(load));
		}
		return out;
	}
	
	private synchronized static EditAction besLoad(List<String> in) {
		String l1 = in.get(0);
		String l2 = in.get(1);
		String l3 = in.get(2);
		String l4 = in.get(3);
		String l5 = in.get(4);
		String l6 = in.get(5);
		String player = l1;
		EditActionType type = EditActionType.valueOf(l2);
		String[] g1 = l3.split(" "), g2 = l4.split(" ");
		Location startLoc = WorldUtil.getLocation(g1[0], g1[1], g1[2], g1[3]);
		Location endLoc = WorldUtil.getLocation(g2[0], g2[1], g2[2], g2[3]);
		Date time = new Date();
		time.setTime(Long.parseLong(l6));
		if(type.equals(EditActionType.BIOME)) {
			List<Biome> before = de.chrissx.cbm.Decompressor.decompress(l5);
			return new EditActionBiome(WorldUtil.blocksFromTwoPoints(startLoc, endLoc), before, player);
		}else {
			List<Material> before = de.chrissx.cbb.Decompressor.decompress(l5);
			switch(type) {
			case WALLS: return new EditActionWalls(WorldUtil.blocksFromTwoPoints(startLoc, endLoc), before, player);
			case SET: return new EditActionSet(WorldUtil.blocksFromTwoPoints(startLoc, endLoc), before, player);
			case BIOME: return new EditAction(WorldUtil.blocksFromTwoPoints(startLoc, endLoc), player);
			case RAW: return new EditAction(WorldUtil.blocksFromTwoPoints(startLoc, endLoc), player);
			default: return new EditAction(WorldUtil.blocksFromTwoPoints(startLoc, endLoc), player);
			}
		}
	}
}