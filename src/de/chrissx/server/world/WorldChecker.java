package de.chrissx.server.world;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.WorldCreator;

import de.chrissx.server.shared.P;
import de.chrissx.server.shared.Util;

public class WorldChecker {
	
	public static synchronized void checkWorlds() {
		Util.runAsyncTask(new Runnable(){
		@Override
		public void run() {
			File[] files = P.SERVER_FOLDER.toFile().listFiles();
			
				for(File f : files) {
					if(f.isDirectory()) {
						if(new File(f.getPath() + File.separator + "level.dat").exists()) {
							String name = f.getName();
							List<String> names = new ArrayList<String>();
							for(World w : Util.getServer().getWorlds()) {
								names.add(w.getName());
							}
							if(!names.contains(name)) {
								WorldCreator create = new WorldCreator(name);
								World worldtoadd = create.createWorld();
								Util.getServer().getWorlds().add(worldtoadd);
							}
						}
					}
				}
			}
		});
	}
}