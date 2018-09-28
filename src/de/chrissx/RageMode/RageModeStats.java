package de.chrissx.RageMode;

import org.bukkit.entity.Player;

import de.chrissx.server.stats.PlayerStats;

public class RageModeStats extends PlayerStats {

	public RageModeStats(Player p, long kills, long deaths, long played, long won) {
		super(p, kills, deaths, played, won);
	}
	
	public RageModeStats() {
		super();
	}

}