package de.chrissx.cbm;

import java.util.List;

import org.bukkit.block.Biome;

import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.Util;

public class Compressor {

	public static synchronized String compress(List<Biome> in) {
		String out = "";
		int i = 0;
		Biome last = Biome.SKY;
		if(in == null) {
			Util.log("ERROR: NULL", CC.RED);
			return "";
		}
		for(Biome b : in) {
			if(b != null) {
				if(b == last) {
					i++;
				}else {
					out+=" "+Integer.toString(i);
					i = 1;
					last = b;
					out+=" "+b.toString();
				}
			}
		}
		out+=" "+Integer.toString(i);
		if(out.startsWith(" 0 ")) {
			out = out.split(" 0 ")[0];
		}
		return out;
	}
}