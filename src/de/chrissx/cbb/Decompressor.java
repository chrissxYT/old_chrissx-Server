package de.chrissx.cbb;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import de.chrissx.server.shared.MathUtil;

public class Decompressor {

	public static synchronized List<Material> decompress(String in) {
		String[] splitted = in.split(" ");
		List<Material> out = new ArrayList<Material>();
		Material currentMaterial = Material.CHAINMAIL_HELMET;
		for(int i = 0; i < splitted.length; i++) {
			if(MathUtil.isNumber(splitted[i])) {
				for(int a = 0; a < Integer.parseInt(splitted[i]); a++) {
					out.add(currentMaterial);
				}
			}else {
				if(!(splitted[i] == "null") && splitted[i] != null) {
					currentMaterial = Material.getMaterial(splitted[i].toUpperCase());
					out.add(currentMaterial);
				}
			}
		}
		return out;
	}
}