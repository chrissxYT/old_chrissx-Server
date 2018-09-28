package de.chrissx.server.mainplugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Location;

import de.chrissx.server.file.FileChecker;
import de.chrissx.server.file.FileCompressor;
import de.chrissx.server.file.FileUtil;
import de.chrissx.server.file.FolderChecker;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.GameLoader;
import de.chrissx.server.shared.P;
import de.chrissx.server.shared.RageModeGame;
import de.chrissx.server.world.WorldChecker;
import de.chrissx.server.world.WorldUtil;

public class Checker {

	public static synchronized void check(Reflector refl) throws IOException {
		FolderChecker.checkFolders();
		FileChecker.checkFiles();
		WorldChecker.checkWorlds();
		for(List<String> str : GameLoader.getGames(P.RAGEMODE_GAMES, P.RAGEMODE_GAMES_EXTENTION)) {
			String name = str.get(0);
			String[] lobbyarray = str.get(1).split(" ");
			String[] arenaarray = str.get(2).split(" ");
			String[] backarray = str.get(3).split(" ");
			Location lobby = WorldUtil.getLocation(lobbyarray[0], lobbyarray[1], lobbyarray[2], lobbyarray[3]);
			Location arena = WorldUtil.getLocation(arenaarray[0], arenaarray[1], arenaarray[2], arenaarray[3]);
			Location back = WorldUtil.getLocation(backarray[0], backarray[1], backarray[2], backarray[3]);
			refl.addRageModeGame(new RageModeGame(name, lobby, arena, back));
		}
		for(File f : FileUtil.list(P.LOG_FOLDER.toFile(), ".zip")) {
			try {
				FileUtil.unzip(f.toPath().toString(), f.getParent().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			f.delete();
		}
		FileCompressor.checkFiles(P.LOG_FOLDER.toFile());
	}
}