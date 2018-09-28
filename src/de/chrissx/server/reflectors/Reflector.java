package de.chrissx.server.reflectors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.chrissx.bes.Loader;
import de.chrissx.playerlist.PlayerList;
import de.chrissx.playerlist.PlayerListLoader;
import de.chrissx.server.blockedit.editactions.EditAction;
import de.chrissx.server.file.FileLoader;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.Logger;
import de.chrissx.server.shared.P;
import de.chrissx.server.shared.RageModeGame;
import de.chrissx.server.shared.Util;

public class Reflector {
	
//
//
//
//
//				   DECLARES & INITS
//	  	    		     AKA
//	      			   VAR-HELL
//
//
//
//
	
	private Map<Player, Boolean> inGame = new HashMap<Player, Boolean>();
	private Map<Player, GameMode> currGame = new HashMap<Player, GameMode>();
	private Map<Player, RageModeGame> currRMGame = new HashMap<Player, RageModeGame>();
	private List<RageModeGame> RMgames = new ArrayList<RageModeGame>();
	private PlayerListLoader CMDSPYloader = new PlayerListLoader(P.CMDSPY_LISTENERS_PATH, Util.getServer(), P.CMDSPY_LISTENERS_EXTENTION);
	private PlayerList CMDSPYlisteners = CMDSPYloader.getPlayers();
	private Logger logger = new Logger(P.LOG_FOLDER, P.LOG_EXTENTION, "");
	private Map<Player, Location[]> locations = new HashMap<Player, Location[]>();
	private List<EditAction> lastActions = new ArrayList<EditAction>();
	private Plugin plugin = Util.getPlugin();
	private PlayerListLoader playerLoader = new PlayerListLoader(P.ANTIGRIEF_PLAYER_PATH, plugin.getServer(), P.ANTIGRIEF_PLAYER_EXTENTION);
	private PlayerList AGlisteners = playerLoader.getPlayers();
	private boolean tnt_chat_out = false, tnt_auto_kick = false;
	
	
//
//
//
//
//			     GETTERS AND SETTERS
//  	    		     AKA
//      			METHOD HELL
//
//
//
//
	
	public void init() throws IOException {
		lastActions = Loader.loadActions(P.ACTION_FILE, P.ACTION_EXTENTION);
	}
	
	public void clearActions() {
		lastActions.clear();
	}
	
	public List<EditAction> getActions() {
		return lastActions;
	}
	
	public boolean isInGame(Player p) {
		if(!inGame.containsKey(p)) {
			return false;
		}
		return inGame.get(p);
	}
	
	public GameMode getCurrentGame(Player p) {
		if(!currGame.containsKey(p)) {
			return GameMode.NONE;
		}
		return currGame.get(p);
	}
	
	public void setInGame(Player p, boolean b) {
		if(inGame.containsKey(p)) {
			inGame.remove(p);
		}
		inGame.put(p, b);
	}
	
	public void setCurrentGame(Player p, GameMode g) {
		if(currGame.containsKey(p)) {
			currGame.remove(p);
		}
		currGame.put(p, g);
	}
	
	public void setCurrentRageModeGame(Player p, RageModeGame g) {
		if(currRMGame.containsKey(p)) {
			currRMGame.remove(p);
		}
		currRMGame.put(p, g);
	}
	
	public RageModeGame getCurrentRageModeGame(Player p) {
		return currRMGame.get(p);
	}
	
	public void addRageModeGame(RageModeGame g) {
		RMgames.add(g);
	}
	
	public RageModeGame getRageModeGame(int i) {
		return RMgames.get(i);
	}
	
	public List<RageModeGame> getRageModeGames() {
		return RMgames;
	}
	
	public RageModeGame getRageModeGame(String name) {
		for(RageModeGame g : RMgames) {
			if(g.getName().equalsIgnoreCase(name)) {
				return g;
			}
		}
		return new RageModeGame("ERROR IN REFLECTOR:129-135 : NOT FOUND", new Location(Bukkit.getWorlds().get(0), 0, 100, 0), new Location(Bukkit.getWorlds().get(0), 0, 100, 0), new Location(Bukkit.getWorlds().get(0), 0, 100, 0));
	}
	
	public boolean isInRMGame(Player p) {
		if(!isInGame(p)) {
			return false;
		}
		if(!getCurrentGame(p).equals(GameMode.RAGEMODE)) {
			return false;
		}
		return true;
	}
	
	public List<EditAction> getActions(String p) {
		List<EditAction> out = new ArrayList<EditAction>();
		for(EditAction e : lastActions) {
			if(e.getPlayer().equals(p)) {
				out.add(e);
			}
		}
		return out;
	}
	
	public void addAction(EditAction a) {
		lastActions.add(a);
	}
	
	public void removeActions(String p, int i) {
		for(int a = 0; a < i; a++) {
			removeLastAction(p);
		}
	}
	
	public void removeLastAction(String p) {
		int index = -1;
		for(int i = 0; i < lastActions.size(); i++) {
			if(lastActions.get(i).getPlayer().equals(p)) {
				index = i;
			}
		}
		if(index == -1) {
			return;
		}
		lastActions.remove(index);
	}
	
	public void addCMDSPYListener(Player p) {
		CMDSPYlisteners.addPlayer(p);
	}
	
	public void removeCMDSPYListener(Player p) {
		CMDSPYlisteners.removePlayer(p);
	}
	
	public PlayerList getCMDSPYListeners() {
		return CMDSPYlisteners;
	}

	public Logger getLogger() {
		return logger;
	}
	
	public boolean isTnt_chat_out() throws IOException {
		tnt_chat_out = Boolean.parseBoolean(FileLoader.getFirstLine(P.ANTIGRIEF_TNT_OUT_FILE));
		return tnt_chat_out;
	}

	public boolean isTnt_auto_kick() throws IOException {
		tnt_chat_out = Boolean.parseBoolean(FileLoader.getFirstLine(P.ANTIGRIEF_TNT_KICK_FILE));
		return tnt_auto_kick;
	}
	
	public void addAGListener(Player p) {
		AGlisteners.addPlayer(p);
	}
	
	public void removeAGListener(Player p) {
		AGlisteners.removePlayer(p);
	}
	
	public void removeAGListener(int i) {
		AGlisteners.removePlayer(i);
	}
	
	public PlayerList getAGListeners() {
		return AGlisteners;
	}
	
	public void setLoc1(Player p, Location l) {
		Util.sendMsg(p, CC.GOLD, "Set postition one to x"+l.getBlockX()+" y"+l.getBlockY()+" z"+l.getBlockZ());
		if(!locations.containsKey(p)) {
			Location[] locs = new Location[] {
					l,
					new Location(Bukkit.getWorlds().get(0), 0, 100, 0)
			};
			locations.put(p, locs);
			return;
		}
		Location[] locs = locations.get(p);
		locs[0] = l;
		locations.remove(p);
		locations.put(p, locs);
	}
	
	public void setLoc2(Player p, Location l) {
		Util.sendMsg(p, CC.GOLD, "Set postition two to x"+l.getBlockX()+" y"+l.getBlockY()+" z"+l.getBlockZ());
		if(!locations.containsKey(p)) {
			Location[] locs = new Location[] {
					new Location(Bukkit.getWorlds().get(0), 0, 100, 0),
					l
			};
			locations.put(p, locs);
			return;
		}
		Location[] locs = locations.get(p);
		locs[1] = l;
		locations.remove(p);
		locations.put(p, locs);
	}
	
	public Location getLoc1(Player p) {
		return locations.get(p)[0];
	}
	
	public Location getLoc2(Player p) {
		return locations.get(p)[1];
	}
	
	public Location[] getLocations(Player p) {
		return locations.get(p);
	}
	
	public void setLocations(Player p, Location[] locs) {
		if(locations.containsKey(p)) {
			locations.remove(p);
		}
		locations.put(p, locs);
	}
}