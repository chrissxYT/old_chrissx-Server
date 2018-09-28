package de.chrissx.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;
import de.chrissx.server.skins.SkinUtil;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class Vanish extends CmdExec {

	public Vanish(Reflector refl) {
		super(refl);
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String __FU__, String[] args) {
		if(c.getName().equalsIgnoreCase("v")) {
			return vanish(s, c, args);
		}else if(c.getName().equalsIgnoreCase("vanish")) {
			return vanish(s, c, args);
		}
		return false;
	}
	
	private boolean vanish(CommandSender s, Command c, String[] args) {
		if(!CommandChecker.check(s, true, true, 0, 0, args)) {
			return true;
		}
		
		CraftPlayer p = (CraftPlayer)s;
		PacketPlayOutEntityDestroy p1 = new PacketPlayOutEntityDestroy(p.getEntityId());
		PacketPlayOutPlayerInfo p2 = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, p.getHandle());
		PacketPlayOutNamedEntitySpawn p3 = new PacketPlayOutNamedEntitySpawn(p.getHandle());
		PacketPlayOutPlayerInfo p4 = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, p.getHandle());
		if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
			p.removePotionEffect(PotionEffectType.INVISIBILITY);
			SkinUtil.sendPacket(p3);
			SkinUtil.sendPacket(p4);
			p.sendMessage(ChatColor.GRAY + "You are no longer invisible.");
		}else {
			PotionEffect invis = new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 0, true);
			p.addPotionEffect(invis);
			SkinUtil.sendPacket(p1);
			SkinUtil.sendPacket(p2);
			p.sendMessage(ChatColor.GRAY + "You are now invisible.");
		}
		return true;
	}

	@Override
	public void init() {
		Util.registerCommand("v", this);
		Util.registerCommand("vanish", this);
	}
}