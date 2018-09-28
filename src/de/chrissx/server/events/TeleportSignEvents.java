package de.chrissx.server.events;

import java.io.File;
import java.nio.file.Paths;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import de.chrissx.server.file.FileLoader;
import de.chrissx.server.file.FileWriter;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandError;
import de.chrissx.server.shared.P;
import de.chrissx.server.shared.Util;

public class TeleportSignEvents extends Ev {
	
	private Plugin plugin;
	
	public TeleportSignEvents() {
		this.plugin = Util.getPlugin();
	}
	
	public void onSignChange(SignChangeEvent event) {
		Player p = event.getPlayer();
		if(!(event.getLine(0) == "[TP]")) {
			return;
		}
		if(!p.isOp()) {
			Util.sendError(p, CommandError.NO_OP);
			return;
		}
		try {
			String[] crds = event.getLine(1).split(",");
			String w = event.getLine(2);
			int x = Integer.parseInt(crds[0]), y = Integer.parseInt(crds[1]), z = Integer.parseInt(crds[2]);
			Location oldloc = p.getLocation();
			Location signloc = event.getBlock().getLocation();
			p.teleport(new Location(plugin.getServer().getWorld(w), x, y, z));
			p.teleport(oldloc);
			File f = new File(P.TP_SIGN_LOCATIONS.toString(), signloc.getWorld().toString() + "_" + signloc.getBlockX() + "_" + signloc.getBlockY() + "_" + signloc.getBlockZ() + P.TP_SIGN_EXTENTION);
			String s = w + "\n" + x + "\n" + y + "\n" + z;
			FileWriter.write(f, s);
			String title = event.getLine(3);
			event.setLine(2, "");
			event.setLine(3, "");
			event.setLine(1, title.replaceAll("&", "§"));
		}catch(Exception e) {
			Util.sendMsg(p, CC.RED, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getType() == Material.SIGN) {
				Sign sign = (Sign)event.getClickedBlock();
				if(sign.getLine(0) == "[TP]") {
					try {
						Player p = event.getPlayer();
						Location l = sign.getLocation();
						String[] tp_coords = (String[]) FileLoader.getText(Paths.get(P.TP_SIGN_LOCATIONS.toString(), l.getWorld().toString() + "_" + l.getBlockX() + "_" + l.getBlockY() + "_" + l.getBlockZ() + P.TP_SIGN_EXTENTION)).toArray();
						int x = Integer.parseInt(tp_coords[1]), y = Integer.parseInt(tp_coords[2]), z = Integer.parseInt(tp_coords[3]);
						World w = plugin.getServer().getWorld(tp_coords[0]);
						p.teleport(new Location(w, x, y, z));
					}catch(Exception e) {
						Util.sendMsg(event.getPlayer(), CC.RED, e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public void init() {
		Util.registerEvents(this);
	}
}