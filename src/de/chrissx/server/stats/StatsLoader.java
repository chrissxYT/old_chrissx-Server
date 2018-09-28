package de.chrissx.server.stats;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.bukkit.entity.Player;

import de.chrissx.server.file.FileLoader;

public class StatsLoader {

	public static PlayerStats load(Path path, Player player) throws IOException {
		PlayerStats output = new PlayerStats();
		List<String> read = FileLoader.getText(path);
		output.setKills(Long.parseLong(read.get(0)));
		output.setDeaths(Long.parseLong(read.get(1)));
		output.setPlayed(Long.parseLong(read.get(2)));
		output.setWon(Long.parseLong(read.get(3)));
		output.setPlayer(player);
		return output;
	}
}