package de.chrissx.server.commands;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.Util;

public abstract class CmdExec implements CommandExecutor {

	public abstract void init();
	
	protected Reflector refl;
	protected Plugin plugin = Util.getPlugin();
	protected Server server = plugin.getServer();
	protected boolean t = true, f = false;
	
	public CmdExec(Reflector refl) {
		this.refl = refl;
	}
	
	@Override
	public abstract boolean onCommand(CommandSender s, Command c, String var69, String[] args);
}