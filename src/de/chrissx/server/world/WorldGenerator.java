package de.chrissx.server.world;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.Util;

public class WorldGenerator {
	
	public static void generateWorld(WorldGeneratorArgs args) {
		WorldCreator wc = new WorldCreator(args.getWorldName());
		Player p = args.getPlayer();
		if(args.hasEnvironment()) {
			wc.environment(args.getEnv());
		}else {
			wc.environment(Environment.NORMAL);
		}
		
		wc.generateStructures(args.getGenerateStructures());
		
		if(args.hasSeed()) {
			wc.seed(args.getSeed());
		}
		
		World w = wc.createWorld();
		
		if(args.hasPlayer())
		Util.sendMsg(p, CC.GREEN, "Successfully created new world \"" + args.getWorldName() + "\"");

		Util.getServer().getWorlds().add(w);
	}
}
