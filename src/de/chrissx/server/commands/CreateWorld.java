package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;
import de.chrissx.server.world.WorldGenerator;
import de.chrissx.server.world.WorldGeneratorArgs;

public class CreateWorld extends CmdExec {

	public CreateWorld(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase("create")) {
			if(!(CommandChecker.check(s, true, true, 1, 4, args))) {
				return true;
			}
			
			WorldGeneratorArgs wgArgs = new WorldGeneratorArgs();
			Player p = (Player)s;
			wgArgs.setPlayer(p);
			wgArgs.setWorldName(args[0]);
			if(args.length >= 2) {
				wgArgs.setEnv(wgArgs.getEnv(args[1]));
			}
			if(args.length >= 3) {
				wgArgs.setSeed(Long.parseLong(args[2]));
			}
			if(args.length == 4) {
				wgArgs.setGenerateStructures(Boolean.parseBoolean(args[3]));
			}
			WorldGenerator.generateWorld(wgArgs);
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand("create", this);
	}

}