package de.chrissx.server.shared;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

import de.chrissx.server.file.FileWriter;

public class GameWriter {

	public static void writeGames(Path base_path, String ext, List<? extends Game> games) throws IOException {
		for(Game g : games) {
			String name = g.getName();
			String lobby = buildLine(g.getLobby());
			String arena = buildLine(g.getArena());
			String back = buildLine(g.getBack());
			Path path = Paths.get(base_path.toString(), g.getName() + ext);
			if(!path.toFile().exists()) {
				Files.createFile(path);
			}
			FileWriter.write(path.toFile(), name + "\n" + lobby + "\n" + arena + "\n" + back);
		}
	}
	
	public static String buildLine(Location l) {
		int x_ = l.getBlockX(), y_ = l.getBlockY(), z_ = l.getBlockZ();
		World w_ = l.getWorld();
		String x = Integer.toString(x_), y = Integer.toString(y_), z = Integer.toString(z_), w = w_.getName();
		return x + " " + y + " " + z + " " + w;
	}
}
