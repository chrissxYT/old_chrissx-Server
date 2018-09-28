package de.chrissx.playerlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.entity.Player;

public class PlayerList implements Iterable<Player> {

	private List<Player> players = new ArrayList<Player>();
	
	public int count() {
		return players.size();
	}
	
	public List<Player> toArrayList(){
		return players;
	}
	
	public Player[] toArray() {
		return (Player[]) players.toArray();
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public void removePlayer(int i) {
		players.remove(i);
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}

	@Override
	public Iterator<Player> iterator() {
		return toArrayList().iterator();
	}
}