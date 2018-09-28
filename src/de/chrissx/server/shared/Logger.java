package de.chrissx.server.shared;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import de.chrissx.server.antigrief.AntiGriefEvent;
import de.chrissx.server.antigrief.AntiGriefEventType;
import de.chrissx.server.blockedit.BlockEditLogAction;
import de.chrissx.server.blockedit.BlockEditLogActionType;
import de.chrissx.server.file.FileWriter;

public class Logger {

	protected Path folder;
	protected String extention = ".log", prefix = "LOG_";
	protected StringBuilder builder = new StringBuilder();
	
	public Logger(Path folder, String extention, String prefix) {
		this.folder = folder;
		this.extention = extention;
		this.prefix = prefix;
	}
	
	public String writeLog() throws IOException {
			Path file = Paths.get(folder.toString(), prefix + new Date().toString().replaceAll(":", "-").replaceAll(" ", "_").replaceAll("CEST_", "").substring(8).replace("CET_", "") + extention);
			int i = 2;
			while(file.toFile().exists()) {
				file = Paths.get(folder.toString(), prefix + new Date().toString().replaceAll(":", "-").replaceAll(" ", "_").replaceAll("CEST_", "").substring(8).replace("CET_", "") + "_" + Integer.toString(i) + extention);
				i++;
			}
			Files.createFile(file);
			FileWriter.write(file.toFile(), builder.toString());
			builder = new StringBuilder();
			return file.toString();
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Path getFolder() {
		return folder;
	}

	public String getExtention() {
		return extention;
	}
	
	public void log(String s) {
		builder.append(s);
	}

	public void setFolder(Path folder) {
		this.folder = folder;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}
	
	public void log(Player p, String cmd) {
		Location l = p.getLocation();
		this.log(p.getName() + "(" + p.getUniqueId() + ") {\nType: COMMAND\nTime: " + new Date().toString().replaceAll(":", "-").replaceAll(" ", "_").replaceAll("CEST_", "").substring(4) + "\nCommand: " + cmd + "\nX: " + l.getBlockX() + "\nY: " + l.getBlockY() + "\nZ: " + l.getBlockZ() + "\n}\n\n");
	}
	
	public void log(BlockEditLogAction action) {
		Player player = action.getPlayer();
		BlockEditLogActionType type = action.getType();
		Location loc = action.getLoc();
		Material block = action.getBlock();
		String towrite = player.getName() + "(" + player.getUniqueId().toString() + ") {\nType: " + type + "\nTime: " + new Date().toString().replaceAll(":", "").replaceAll(" ", "_").replaceAll("CEST_", "").substring(4) + "\nBlock: " + block + "\nX: " + loc.getBlockX() + "\nY: " + loc.getBlockY() + "\nZ: " + loc.getBlockZ() + "\n}\n\n";
		this.log(towrite);
	}
	
	public void log(AntiGriefEvent event) {
		String f = "";
		String b = event.getExtraInformation();
		Entity p = event.getEntity();
		AntiGriefEventType type = event.getType();
		Location loc = event.getLocation();
		switch(event.getType()) {
		case PLACE: f = "Block: " + b + "\n"; break;
		case BREAK: f = "Block: " + b + "\n"; break;
		case COMMAND: f = "Command: " + b + "\n"; break;
		case CHAT: f = "Message: " + b + "\n"; break;
		case IGNITION: f = ""; break;
		case ENTITY_EXPLOSION: f = ""; break;
		case BLOCK_EXPLOSION: f = ""; break;
		default: f = "CHRISSX IS TOO SILLY TO ADD NEW EVENT TYPES TO ANTIGRIEFLOGGER..."; break;
		}
		String towrite = p.getName() + "(" + p.getUniqueId().toString() + ") {\nType: " + type + "\nTime: " + new Date().toString().replaceAll(":", "-").replaceAll(" ", "_").replaceAll("CEST_", "").substring(4) + "\n" + f + "X: " + loc.getBlockX() + "\nY: " + loc.getBlockY() + "\nZ: " + loc.getBlockZ() + "\n}\n\n";
		this.log(towrite);
		//Console
		String g = "";
		switch(type) {
		case PLACE: g = " placed " + b + " "; break;
		case BREAK: g = " broke " + b + " "; break;
		case COMMAND: g = " executed " + b + " "; break;
		case CHAT: g = " said " + b + " "; break;
		case IGNITION: g = " ignited TNT "; break;
		case BLOCK_EXPLOSION: g = " exploded "; break;
		case ENTITY_EXPLOSION: g = " exploded "; break;
		}
		System.out.println(p.getName() + g + "@ x" + loc.getBlockX() + " y" + loc.getBlockY() + " z" + loc.getBlockZ());
}
}
