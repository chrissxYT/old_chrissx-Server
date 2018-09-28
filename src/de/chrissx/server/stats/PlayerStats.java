package de.chrissx.server.stats;

import org.bukkit.entity.Player;

public class PlayerStats {

	protected long kills, deaths, played, won;
	protected float kd;
	protected int ranking, winRate;
	protected Player player;
	
	public PlayerStats() {
		
	}
	
	public PlayerStats(Player p, long kills, long deaths, long played, long won) {
		this.kills = kills;
		this.deaths = deaths;
		this.played = played;
		this.won = won;
		update();
	}

	public long getKills() {
		update();
		return kills;
	}

	public void setKills(long kills) {
		this.kills = kills;
		update();
	}

	public long getDeaths() {
		update();
		return deaths;
	}

	public void setDeaths(long deaths) {
		this.deaths = deaths;
		update();
	}

	public long getPlayed() {
		update();
		return played;
	}

	public void setPlayed(long played) {
		this.played = played;
		update();
	}

	public long getWon() {
		update();
		return won;
	}

	public void setWon(long won) {
		this.won = won;
		update();
	}

	public float getKd() {
		update();
		return kd;
	}

	public int getRanking() {
		update();
		return ranking;
	}

	public int getWinRate() {
		update();
		return winRate;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	protected void calcWinRate() {
		winRate = (int) ((won / played) * 100);
	}
	
	protected void calcKd() {
		kd = kills / deaths;
	}
	
	protected void calcRanking() {
		
	}
	
	public void update() {
		calcKd();
		calcRanking();
		calcWinRate();
	}
}
