package de.chrissx.server.blockedit.editactions;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class EditActionBlocks extends EditAction {
	
	protected List<Material> before;
	
	public EditActionBlocks(List<Block> blocks, String player, List<Material> before) {
		super(blocks, player);
		this.before = before;
	}
	
	public List<Material> getBefore() {
		return before;
	}
}