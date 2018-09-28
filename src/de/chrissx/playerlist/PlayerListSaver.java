package de.chrissx.playerlist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.chrissx.server.file.FileWriter;

public class PlayerListSaver {

	private Path folder;
	private PlayerList players;
	private String extention = ".txt";
	
	public PlayerListSaver(Path folder, String extention, PlayerList players) {
		this.folder = folder;
		this.extention = extention;
		this.players = players;
	}
	
	public void savePlayers(Plugin plugin) throws IOException {
		for(Player p : players.toArrayList()) {
			Path path = Paths.get(folder.toString(), p.getName() + extention);
			if(!path.toFile().exists()) {
				Files.createFile(path);
			}
			FileWriter.write(path.toFile(), "true");
		}
	}
}