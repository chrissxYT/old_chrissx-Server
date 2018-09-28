package de.chrissx.server.blockedit.editactions;

import java.util.List;

import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class EditActionBiomes extends EditAction {

	protected List<Biome> before;
	
	public EditActionBiomes(List<Block> blocks, String player, List<Biome> before) {
		super(blocks, player);
		this.before = before;
	}
	
	public List<Biome> getBefore() {
		return before;
	}
}