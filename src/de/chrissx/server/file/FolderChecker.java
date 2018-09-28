package de.chrissx.server.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.chrissx.server.shared.P;

public class FolderChecker {

	private static Path[] folders = new Path[] {
			Paths.get(P.CUSTOM_PLUGINS_FOLDER),
			Paths.get(P.RAGEMODE_BASE_PATH),
			P.RAGEMODE_GAMES,
			P.RAGEMODE_STATS,
			P.LOG_FOLDER,
			P.ANTIGRIEF_PLAYER_PATH,
			P.CONFIG_PATH,
			P.PLAYER_HOMES,
			P.TP_SIGN_LOCATIONS,
			P.CMDSPY_LISTENERS_PATH,
	};
	
	public static void checkFolders() throws IOException {
		for(Path f : folders) {
			if(!f.toFile().exists()) {
				Files.createDirectory(f);
			}
		}
	}
}
