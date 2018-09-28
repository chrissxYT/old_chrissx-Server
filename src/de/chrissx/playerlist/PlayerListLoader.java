package de.chrissx.playerlist;

import java.io.File;
import java.nio.file.Path;

import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.chrissx.server.file.FileUtil;

public class PlayerListLoader {

	private Path folder;
	private PlayerList players = new PlayerList();
	private Server server;
	private String extention;
	
	public PlayerListLoader(Path folder, Server s, String extention) {
		this.folder = folder;
		this.server = s;
		this.extention = extention;
	}
	
	private void loadPlayers() {
		for(File f : FileUtil.list(folder.toFile(), extention)) {
			try {
				Player pl = server.getPlayer(f.getName().replace(extention, ""));
				players.addPlayer((CraftPlayer)pl);
				break;
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public PlayerList getPlayers() {
		loadPlayers();
		return players;
	}
}
