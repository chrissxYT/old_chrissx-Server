package de.chrissx.server.world;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import de.chrissx.server.blockedit.editactions.EditAction;
import de.chrissx.server.blockedit.editactions.EditActionBiomes;
import de.chrissx.server.blockedit.editactions.EditActionBlocks;
import de.chrissx.server.blockedit.editactions.EditActionType;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandError;
import de.chrissx.server.shared.Util;

public class WorldUtil {

	public static boolean deleteWorld(File path) {
		if(path.exists()) {
			File files[] = path.listFiles();
			for(int i=0; i<files.length; i++) {
				if(files[i].isDirectory()) {
					deleteWorld(files[i]);
				}else {
					files[i].delete();
				}
			}
		}
		return(path.delete());
	}
	
	public static List<Block> blocksFromTwoPoints(Location loc1, Location loc2) {
        List<Block> blocks = new ArrayList<Block>();
 
        int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
        int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
 
        int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
        int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
 
        int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
 
        for(int x = bottomBlockX; x <= topBlockX; x++)
        {
            for(int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                for(int y = bottomBlockY; y <= topBlockY; y++)
                {
                    blocks.add(getBlock(new Location(loc1.getWorld(), x, y, z)));
                }
            }
        }
       
        return blocks;
    }
	
	public static Block getBlock(Location l) {
		return l.getWorld().getBlockAt(l);
	}
	
	public static List<Block> getWalls(Location center, int radius) {
		List<Block> blocks = new ArrayList<Block>();
		
		int x = center.getBlockX(), y = center.getBlockY(), z = center.getBlockZ();
		World w = center.getWorld();
		
		for(Block b : blocksFromTwoPoints(new Location(w, x - radius, y + radius, z - radius), new Location(w, x + radius, y, z - radius))) {
			blocks.add(b);
		}
		
		for(Block b : blocksFromTwoPoints(new Location(w, x - radius, y + radius, z - radius), new Location(w, x - radius, y, z + radius))) {
			blocks.add(b);
		}
		
		for(Block b : blocksFromTwoPoints(new Location(w, x + radius, y + radius, z + radius), new Location(w, x + radius, y, z - radius))) {
			blocks.add(b);
		}
		
		for(Block b : blocksFromTwoPoints(new Location(w, x + radius, y + radius, z + radius), new Location(w, x - radius, y, z + radius))) {
			blocks.add(b);
		}
		
		return blocks;
	}
	
	public static Location getLocation(String x, String y, String z, String w) {
		int parsedX = Integer.parseInt(x);
		int parsedY = Integer.parseInt(y);
		int parsedZ = Integer.parseInt(z);
		World parsedWorld = Bukkit.getWorld(w);
		return new Location(parsedWorld, parsedX, parsedY, parsedZ);
	}
	
	public static boolean isDay(World world) {
	    long time = world.getTime();

	    return time < 12001 || time > 23999;
	}
	
	public static boolean isUnderAir(Player p) {
		int x = p.getLocation().getBlockX(), z = p.getLocation().getBlockZ();
		World w = p.getLocation().getWorld();
		boolean ua = true;
		
		for(int y = p.getLocation().getBlockY(); y <= 256; y++) {
			if(getBlock(new Location(w, x, y, z)).getType() != Material.AIR) {
				ua = false;
			}
		}
		
		return ua;
	}
	
	public static void undoAction(Player p, EditAction action) {
		if(action == null) {
			Util.sendMsg(p, CC.RED, "Nothing to undo.");
			return;
		}
		EditActionType type = action.getType();
		if(type.equals(EditActionType.RAW)) {
			Util.sendError(p, CommandError.ENUM_ERROR, "Not your fault, it's my undoAction(Player p, EditAction action) void.");
			return;
		}else if(action instanceof EditActionBlocks) {
			EditActionBlocks ac = (EditActionBlocks)action;
			List<Block> blocks = ac.getBlocks();
			List<Material> before = ac.getBefore();
			int i = -1;
			for(Block b : blocks) {
				i++;
				b.setType(before.get(i));
			}
		}else if(action instanceof EditActionBiomes) {
			EditActionBiomes ac = (EditActionBiomes)action;
			List<Block> blocks = ac.getBlocks();
			List<Biome> before = ac.getBefore();
			for(int i = 0; i < blocks.size(); i++) {
				blocks.get(i).setBiome(before.get(i));
			}
			return;
		}else {
			Util.log("Error in " + WorldUtil.class.getName() + " : 152", CC.RED);
			return;
		}
	}
	
	public static void undoActions(Player p, int i, Reflector refl) {
		List<EditAction> actions = refl.getActions(p.getName());
		if(actions == null) {
			Util.sendMsg(p, CC.RED, "Nothing to undo.");
			return;
		}else if(actions.size() == 0) {
			Util.sendMsg(p, CC.RED, "Nothing to undo.");
			return;
		}
		for(int a = 1; a <= i; a++) {
			undoAction(p, actions.get(actions.size()-a));
		}
		refl.removeActions(p.getName(), i);
	}
}