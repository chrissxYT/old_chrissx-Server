package de.chrissx.server.blockedit.editactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class EditAction {

	protected List<Block> blocks = new ArrayList<Block>();
	protected String player;
	protected Date time;
	
	public EditAction(List<Block> blocks, String player) {
		this.blocks = blocks;
		this.player = player;
		time = new Date();
	}

	public List<Block> getBlocks() {
		return blocks;
	}
	
	public EditActionType getType() {
		return EditActionType.RAW;
	}
	
	public String getPlayer() {
		return player;
	}

	public Date getTime() {
		return time;
	}
	
	public Location getStartLocation() {
		return blocks.get(0).getLocation();
	}
	
	public Location getEndLocation() {
		return blocks.get(blocks.size()-1).getLocation();
	}
}