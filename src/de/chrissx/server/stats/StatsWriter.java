package de.chrissx.server.stats;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import de.chrissx.server.file.FileWriter;

public class StatsWriter {

	public static void write(PlayerStats stats, Path file) throws IOException {
		String towrite = stats.getKills() + "\n" + stats.getDeaths() + "\n" + stats.getPlayed() + "\n" + stats.getWon();
		File f = file.toFile();
		if(!f.exists()) {
			Files.createFile(f.toPath());
		}
		FileWriter.write(f, towrite);
	}
}