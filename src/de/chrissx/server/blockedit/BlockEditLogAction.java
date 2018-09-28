package de.chrissx.server.blockedit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BlockEditLogAction {

	private BlockEditLogActionType type;
	private Material block;
	private Player player;
	private Location loc;
	
	public BlockEditLogAction(BlockEditLogActionType type, Material block, Player player, Location loc) {
		this.type = type;
		this.block = block;
		this.player = player;
		this.loc = loc;
	}

	public BlockEditLogActionType getType() {
		return type;
	}

	public void setType(BlockEditLogActionType type) {
		this.type = type;
	}

	public Material getBlock() {
		return block;
	}

	public void setBlock(Material block) {
		this.block = block;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}
}