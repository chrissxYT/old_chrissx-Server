package de.chrissx.server.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import com.mojang.authlib.GameProfile;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;
import de.chrissx.server.skins.SkinUtil;

public class Skin extends CmdExec {
	
	public Skin(Reflector refl) {
		super(refl);
	}

	Map<CraftPlayer, GameProfile> default_skins = new HashMap<CraftPlayer, GameProfile>();
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {
		if(c.getName().equalsIgnoreCase(Commands.SKIN_CHANGE_COMMAND)) {
			if(!CommandChecker.check(s, true, false, 1, 1, args)) {
				return true;
			}
			
			CraftPlayer p = (CraftPlayer)s;
			GameProfile skintoload = new GameProfile(null, "ERROR");
			if(args[0].equalsIgnoreCase(p.getName())) {
				skintoload = default_skins.get(p);
			}else {
				try {
					if(!default_skins.containsKey(p)) {
						default_skins.put(p, p.getProfile());
					}
					skintoload = SkinUtil.getSkin(args[0]);
				} catch (IOException e) {
					Util.sendMsg(p, CC.RED, "Error: " + e.getMessage());
					e.printStackTrace();
					return true;
				}
			}
			try {
				SkinUtil.setSkin(p, skintoload);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Util.sendMsg(p, CC.GREEN, "Changed skin.");
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		Util.registerCommand(Commands.SKIN_CHANGE_COMMAND, this);
	}
}