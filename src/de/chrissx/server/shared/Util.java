package de.chrissx.server.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import de.chrissx.server.events.Ev;
import de.chrissx.server.mainplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class Util {
	
	public static void sendMsg(CommandSender s, ChatColor color, String msg) {
		s.sendMessage(color + msg);
	}
	
	public static void sendError(CommandSender s, CommandError e) {
		String msg = "Well, something doesn't work, send this message to chrissx! (Debug: ERROR_MSG_NOT_CHANGED_SENDERROR_2)";
		switch(e) {
		case NO_OP: msg = "You must be OP to do this."; break;
		case NOT_ENOUGH_ARGUMENTS: msg = "Not enough arguments."; break;
		case TOO_MANY_ARGUMENTS: msg = "Too many arguments."; break;
		case NO_PLAYER: msg = "You must be a player to execute this command."; break;
		case WRONG_USAGE: msg = "Wrong usage!"; break;
		case INTERNAL: msg = "Internal Error."; break;
		case PLAYER_NOT_FOUND: msg = "Player not found."; break;
		case ENUM_ERROR: msg = "Can't get the value out of the enum, are you sure, you tiped everything right?"; break;
		case NOT_ADDED_TO_SWITCH_CASE: msg = "Looks like chrissx forgot to add something to a switch-case(please report this to him)."; break;
		default: sendError(s, CommandError.NOT_ADDED_TO_SWITCH_CASE, "de.chrissx.server.shared.Util:42-57"); return;
		}
		sendMsg(s, CC.RED, msg);
	}
	
	public static void sendError(CommandSender s, CommandError e, String ext_msg) {
		String msg = "Well, something doesn't work, send this message to chrissx! (Debug: ERROR_MSG_NOT_CHANGED_SENDERROR_3)";
		switch(e) {
		case NO_OP: msg = "You must be OP to do this"; break;
		case NOT_ENOUGH_ARGUMENTS: msg = "Not enough arguments"; break;
		case TOO_MANY_ARGUMENTS: msg = "Too many arguments"; break;
		case NO_PLAYER: msg = "You must be a player to execute this command"; break;
		case WRONG_USAGE: msg = "Wrong usage"; break;
		case PLAYER_NOT_FOUND: msg = "Player not found"; break;
		case INTERNAL: msg = "Internal error(report 2 chrissx pls)"; break;
		case ENUM_ERROR: msg = "Can't get the value out of the enum, are you sure, you tiped everything right"; break;
		case NOT_ADDED_TO_SWITCH_CASE: msg = "Looks like chrissx forgot to add something to a switch-case(please report this to him)"; break;
		default: sendError(s, CommandError.NOT_ADDED_TO_SWITCH_CASE, "de.chrissx.server.shared.Util:59-74"); return;
		}
		sendMsg(s, CC.RED, msg + ": " + ext_msg);
	}
	
	public static int getPing(Player p) {
		return ((CraftPlayer) p).getHandle().ping;
	}
	
	public static void registerCommand(String cmd, CommandExecutor exec) {
		getPlugin().getCommand(cmd).setExecutor(exec);
	}
	
	public static void registerEvents(Ev l) {
		getServer().getPluginManager().registerEvents(l, getPlugin());
	}
	
	public static final JavaPlugin getPlugin() {
		return Main.getPlugin(Main.class);
	}
	
	public static final Server getServer() {
		return getPlugin().getServer();
	}
	
	public static void log(String s, ChatColor c) {
		getServer().getConsoleSender().sendMessage(c + s);
	}
	
	public static void runAsyncTask(Runnable r) {
		Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), r);
	}
	
	public static void runTask(Runnable r) {
		Bukkit.getScheduler().runTask(getPlugin(), r);
	}
	
	public static FallingBlock getFallingBlock(Block b) {
		return new FallingBlock() {
			@Override
			public boolean eject() {
				return false;
			}
			
			@Override
			public String getCustomName() {
				return b.getType().toString();
			}
			
			@Override
			public int getEntityId() {
				return 0;
			}

			@Override
			public float getFallDistance() {
				return 0;
			}

			@Override
			public int getFireTicks() {
				return 0;
			}

			@Override
			public EntityDamageEvent getLastDamageCause() {
				return null;
			}

			@Override
			public Location getLocation() {
				return b.getLocation();
			}

			@Override
			public Location getLocation(Location arg0) {
				return b.getLocation();
			}

			@Override
			public int getMaxFireTicks() {
				return 0;
			}

			@Override
			public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2) {
				return new ArrayList<Entity>();
			}

			@Override
			public Entity getPassenger() {
				return null;
			}

			@Override
			public Server getServer() {
				return null;
			}

			@Override
			public int getTicksLived() {
				return 0;
			}

			@Override
			public EntityType getType() {
				return EntityType.FALLING_BLOCK;
			}

			@Override
			public UUID getUniqueId() {
				return new UUID(69, 69);
			}

			@Override
			public Entity getVehicle() {
				return null;
			}

			@Override
			public Vector getVelocity() {
				return new Vector();
			}

			@Override
			public World getWorld() {
				return b.getWorld();
			}

			@Override
			public boolean isCustomNameVisible() {
				return false;
			}

			@Override
			public boolean isDead() {
				return false;
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public boolean isInsideVehicle() {
				return false;
			}

			@Override
			public boolean isOnGround() {
				return true;
			}

			@Override
			public boolean isValid() {
				return false;
			}

			@Override
			public boolean leaveVehicle() {
				return false;
			}

			@Override
			public void playEffect(EntityEffect arg0) {
			}

			@Override
			public void remove() {
			}

			@Override
			public void setCustomName(String arg0) {
			}

			@Override
			public void setCustomNameVisible(boolean arg0) {
			}

			@Override
			public void setFallDistance(float arg0) {
			}

			@Override
			public void setFireTicks(int arg0) {
			}

			@Override
			public void setLastDamageCause(EntityDamageEvent arg0) {
			}

			@Override
			public boolean setPassenger(Entity arg0) {
				return false;
			}

			@Override
			public void setTicksLived(int arg0) {
			}

			@Override
			public void setVelocity(Vector arg0) {
			}

			@Override
			public Spigot spigot() {
				return null;
			}

			@Override
			public boolean teleport(Location arg0) {
				return true;
			}

			@Override
			public boolean teleport(Entity arg0) {
				return true;
			}

			@Override
			public boolean teleport(Location arg0, TeleportCause arg1) {
				return true;
			}

			@Override
			public boolean teleport(Entity arg0, TeleportCause arg1) {
				return true;
			}

			@Override
			public List<MetadataValue> getMetadata(String arg0) {
				return null;
			}

			@Override
			public boolean hasMetadata(String arg0) {
				return false;
			}

			@Override
			public void removeMetadata(String arg0, Plugin arg1) {
			}

			@Override
			public void setMetadata(String arg0, MetadataValue arg1) {
			}

			@Override
			public String getName() {
				return b.getType().toString();
			}

			@Override
			public void sendMessage(String arg0) {
			}

			@Override
			public void sendMessage(String[] arg0) {
			}

			@Override
			public PermissionAttachment addAttachment(Plugin arg0) {
				return null;
			}

			@Override
			public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
				return null;
			}

			@Override
			public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {
				return null;
			}

			@Override
			public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {
				return null;
			}

			@Override
			public Set<PermissionAttachmentInfo> getEffectivePermissions() {
				return null;
			}

			@Override
			public boolean hasPermission(String arg0) {
				return false;
			}

			@Override
			public boolean hasPermission(Permission arg0) {
				return false;
			}

			@Override
			public boolean isPermissionSet(String arg0) {
				return false;
			}

			@Override
			public boolean isPermissionSet(Permission arg0) {
				return false;
			}

			@Override
			public void recalculatePermissions() {
			}

			@Override
			public void removeAttachment(PermissionAttachment arg0) {
			}

			@Override
			public boolean isOp() {
				return true;
			}

			@Override
			public void setOp(boolean arg0) {				
			}

			@Override
			public boolean canHurtEntities() {
				return false;
			}

			@Override
			public byte getBlockData() {
				return 0;
			}

			@Override
			public int getBlockId() {
				return 0;
			}

			@Override
			public boolean getDropItem() {
				return false;
			}

			@Override
			public Material getMaterial() {
				return b.getType();
			}

			@Override
			public void setDropItem(boolean arg0) {
			}

			@Override
			public void setHurtEntities(boolean arg0) {
			}
			
		};
	}
}
