package de.chrissx.server.events;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import de.chrissx.playerlist.PlayerListSaver;
import de.chrissx.server.antigrief.AntiGriefEvent;
import de.chrissx.server.antigrief.AntiGriefEventType;
import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.P;
import de.chrissx.server.shared.Util;

public class AntiGriefEvents extends Ev {
	
	private Plugin plugin = Util.getPlugin();
	private PlayerListSaver saver;
	private Reflector refl;
	
	public AntiGriefEvents(Reflector refl) {
		this.refl = refl;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		Location b_loc = block.getLocation();
		AntiGriefEvent e = new AntiGriefEvent(AntiGriefEventType.BREAK, player, b_loc, block.getType().toString());
		refl.getLogger().log(e);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		Location b_loc = block.getLocation();
		refl.getLogger().log(new AntiGriefEvent(AntiGriefEventType.PLACE, player, b_loc, block.getType().toString()));
		if(block.getType().equals(Material.TNT)) {
			StringBuilder msg = new StringBuilder();
			msg.append("TNT: " + player.getName());
			if(player.getDisplayName() != player.getName()) {
				msg.append(" aka. " + player.getDisplayName());
			}
			for(Player p : refl.getAGListeners()) {
				if(p != null) {
					Util.sendMsg(p, CC.RED, msg.toString());
				}
			}
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		String msg = event.getMessage();
		Player player = event.getPlayer();
		Location p_loc = player.getLocation();
		refl.getLogger().log(new AntiGriefEvent(AntiGriefEventType.CHAT, player, p_loc, msg));
	}
	
	@EventHandler
	public void onInteraction(PlayerInteractEvent event) throws IOException {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getType().equals(Material.TNT)) {
				if(event.getPlayer().getItemInHand().getType().equals(Material.FLINT_AND_STEEL)) {
					Player p = event.getPlayer();
					Location b_loc = event.getClickedBlock().getLocation();
					AntiGriefEvent e = new AntiGriefEvent(AntiGriefEventType.IGNITION, p, b_loc, "TNT");
					refl.getLogger().log(e);
					if(refl.isTnt_chat_out()) {
						plugin.getServer().broadcastMessage(CC.RED + p.getName() + " zündet TNT.");
					}
					if(refl.isTnt_auto_kick()) {
						plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "kick " + p.getName() + " Das Zünden von TNT steht meistens in direkter Verbindung zum Griefen!");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityExplosion(EntityExplodeEvent e) {
		refl.getLogger().log(new AntiGriefEvent(AntiGriefEventType.ENTITY_EXPLOSION, e.getEntity(), e.getLocation(), ""));
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockExplosion(BlockExplodeEvent e) {
		refl.getLogger().log(new AntiGriefEvent(AntiGriefEventType.BLOCK_EXPLOSION, Util.getFallingBlock(e.getBlock()), e.getBlock().getLocation(), e.getBlock().getType().name()));
		e.setCancelled(true);
	}
	
	public Runnable saveListeners = new Runnable() {
		@Override
		public void run() {
			saver = new PlayerListSaver(P.ANTIGRIEF_PLAYER_PATH, P.ANTIGRIEF_PLAYER_EXTENTION, refl.getAGListeners());
			try {
				saver.savePlayers(plugin);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	@Override
	public void init() {
		Util.registerEvents(this);
	}
}