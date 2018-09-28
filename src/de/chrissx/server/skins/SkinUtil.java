package de.chrissx.server.skins;

import java.io.IOException;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class SkinUtil {

	public static GameProfile getSkin(String player) throws IOException {
		return GameProfileBuilder.fetch(UUIDFetcher.getUUID(player));
	}
	
	public static void setSkin(CraftPlayer cp, GameProfile skin) throws InterruptedException {
		Collection<Property> props = skin.getProperties().get("textures");
		cp.getProfile().getProperties().removeAll("textures");
		cp.getProfile().getProperties().putAll("textures", props);
		PacketPlayOutEntityDestroy p1 = new PacketPlayOutEntityDestroy(cp.getEntityId());
		PacketPlayOutPlayerInfo p2 = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
		PacketPlayOutNamedEntitySpawn p3 = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
		PacketPlayOutPlayerInfo p4 = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle());
		sendPacket(p1);
		sendPacket(p2);
		Thread.sleep(50);
		updateAll();
		Thread.sleep(50);
		sendPacket(p3);
		for(Player pl : Bukkit.getOnlinePlayers()) {
			if(pl.getName() == cp.getName()) {
				break;
			}
			((CraftPlayer)pl).getHandle().playerConnection.sendPacket(p4);
		}
	}
	
	public static void sendPacket(@SuppressWarnings("rawtypes") Packet p) {
		for(Player pl : Bukkit.getOnlinePlayers()) {
			((CraftPlayer)pl).getHandle().playerConnection.sendPacket(p);
		}
	}
	
	public static void update(Player p) {
		Double hp = p.getHealth();
		Location loc = p.getLocation();
		PlayerInventory i = p.getInventory();
		p.setHealth(0);
		p.spigot().respawn();
		p.setHealth(hp);
		p.teleport(loc);
		p.getInventory().clear();
		p.getInventory().setContents(i.getContents());
		p.getInventory().setArmorContents(i.getArmorContents());
	}
	
	public static void updateAll() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			update(p);
		}
	}
}