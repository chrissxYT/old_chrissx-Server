package de.chrissx.cbb;

import java.util.List;

import org.bukkit.Material;

import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.Util;

public class Compressor {
	
	public static synchronized String compress(List<Material> in) {
		String out = "";
		int i = 0;
		Material last = Material.CHAINMAIL_HELMET;
		if(in == null) {
			Util.log("ERROR: NULL", CC.RED);
			return "";
		}
		for(Material m : in) {
			if(m != null) {
				if(m == last) {
					i++;
				}else {
					out+=" "+Integer.toString(i);
					i = 1;
					last = m;
					out+=" "+m.toString();
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