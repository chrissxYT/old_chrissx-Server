package de.chrissx.server.blockedit.editactions;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class EditActionWalls extends EditActionBlocks {

	public EditActionWalls(List<Block> blocks, List<Material> before, String player) {
		super(blocks, player, before);
	}
	
	@Override
	public EditActionType getType() {
		return EditActionType.WALLS;
	}
}