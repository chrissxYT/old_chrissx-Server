package de.chrissx.server.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.chrissx.server.reflectors.Reflector;
import de.chrissx.server.shared.CC;
import de.chrissx.server.shared.CommandChecker;
import de.chrissx.server.shared.Util;

public class ItemGive extends CmdExec {

	public ItemGive(Reflector refl) {
		super(refl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String FUCK_YOU, String[] args) {
		if(c.getName().equalsIgnoreCase("i")) {
			return itemGive(s, c, args);
		}else if(c.getName().equalsIgnoreCase("item")) {
			return itemGive(s, c, args);
		}else if(c.getName().equalsIgnoreCase("give")) {
			return itemGive(s, c, args);
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	private boolean itemGive(CommandSender s, Command c, String[] args) {
		if(!CommandChecker.check(s, true, true, 1, 2, args)) {
			return true;
		}
		Player p = (Player)s;
		Material i;
		int number;
		try {
			i = Material.getMaterial(Integer.parseInt(args[0]));
		}catch(Exception e) {
			i = Material.getMaterial(args[0].toUpperCase());
		}
		if(args.length > 1) {
			try {
				number = Integer.parseInt(args[1]);
			}catch(Exception e) {
				Util.sendMsg(p, CC.RED, "Error parsing number of items: " + e.toString() + ", continuing with 64");
				number = 64;
			}
		}else {
			number = 64;
		}
		if(i == null) {
			p.sendMessage(CC.RED + "Not an item-ID.");
			return true;
		}
		p.getInventory().addItem(new ItemStack(i, number));
		Util.sendMsg(p, CC.GOLD, "Gave you " + number + " of " + i.name());
		return true;
	}

	@Override
	public void init() {
		Util.registerCommand("i", this);
		Util.registerCommand("item", this);
		Util.registerCommand("give", this);
	}
}