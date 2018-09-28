package de.chrissx.cbm;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Biome;

import de.chrissx.server.shared.MathUtil;

public class Decompressor {

	public static synchronized List<Biome> decompress(String in) {
		String[] splitted = in.split(" ");
		List<Biome> out = new ArrayList<Biome>();
		Biome currentBiome = Biome.SKY;
		for(int i = 0; i < splitted.length; i++) {
			if(MathUtil.isNumber(splitted[i])) {
				for(int a = 0; a < Integer.parseInt(splitted[i]); a++) {
					out.add(currentBiome);
				}
			}else {
				if(!(splitted[i] == "null") && splitted[i] != null) {
					currentBiome = Biome.valueOf(splitted[i].toUpperCase());
					out.add(currentBiome);
				}
			}
		}
		return out;
	}
}