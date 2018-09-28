package de.chrissx.server.blockedit.editactions;

import java.util.List;

import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class EditActionBiome extends EditActionBiomes {
	
	public EditActionBiome(List<Block> blocks, List<Biome> before, String player) {
		super(blocks, player, before);
		this.before = before;
	}

	@Override
	public EditActionType getType() {
		return EditActionType.BIOME;
	}
}